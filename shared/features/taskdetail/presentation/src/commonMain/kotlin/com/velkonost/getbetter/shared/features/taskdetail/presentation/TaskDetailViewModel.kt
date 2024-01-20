package com.velkonost.getbetter.shared.features.taskdetail.presentation

import AreasRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.model.hint.UIHint
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.createnote.api.CreateNoteRepository
import com.velkonost.getbetter.shared.features.createnote.presentation.CreateNewNoteViewModel
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteEvent
import com.velkonost.getbetter.shared.features.diary.api.DiaryRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.taskdetail.api.TaskDetailRepository
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailAction
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailEvent
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailNavigation
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailViewState
import com.velkonost.getbetter.shared.features.tasks.api.TasksRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class TaskDetailViewModel
internal constructor(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository,
    private val tasksRepository: TasksRepository,
    private val areasRepository: AreasRepository,
    private val diaryRepository: DiaryRepository,
    private val taskDetailRepository: TaskDetailRepository,
    private val createNoteRepository: CreateNoteRepository
) : BaseViewModel<TaskDetailViewState, TaskDetailAction, TaskDetailNavigation, TaskDetailEvent>(
    initialState = TaskDetailViewState(),
    savedStateHandle = savedStateHandle
) {

    private val task = savedStateHandle.task.stateInWhileSubscribed(initialValue = null)

    private var _changeNotInterestingJob: Job? = null
    private var _changeCompletedJob: Job? = null

    private val _notesPagingConfig = PagingConfig()
    private var notesLoadingJob: Job? = null

    init {
        fetchTasks()
        fetchAreas()

        launchJob {
            task.collectLatest { task ->
                emit(
                    viewState.value.copy(
                        isLoading = false,
                        task = task,
                        area = task?.area
                    )
                )
            }
        }

        showHint(firstTime = true)
    }

    override fun init() {
        launchJob {
            createNewNoteViewModel.value.viewState.collect {
                emit(viewState.value.copy(createNewNoteViewState = it))
            }
        }

        launchJob {
            createNewNoteViewModel.value.events.collect {
                when (it) {
                    is CreateNewNoteEvent.CreatedSuccess -> emit(TaskDetailEvent.NewNoteCreatedSuccess)
                }
            }
        }
    }

    @NativeCoroutinesState
    val createNewNoteViewModel: StateFlow<CreateNewNoteViewModel> =
        MutableStateFlow(
            CreateNewNoteViewModel(
                notesRepository,
                diaryRepository,
                createNoteRepository
            )
        )

    override fun dispatch(action: TaskDetailAction) = when (action) {
        is TaskDetailAction.NavigateBack -> emit(action)
        is TaskDetailAction.AreaChanged -> obtainAreaChanged()
        is TaskDetailAction.FavoriteClick -> obtainFavoriteClick()
        is TaskDetailAction.NotInterestingClick -> obtainChangeNotInteresting()
        is TaskDetailAction.CompletedClick -> obtainChangeCompleted()
        is TaskDetailAction.CreateGoalClick -> obtainCreateGoal()
        is TaskDetailAction.CreateNoteClick -> obtainCreateDefaultNote()
        is TaskDetailAction.UserNotesLoadNextPage -> fetchUserNotes()
        is TaskDetailAction.RefreshUserNotes -> refreshUserNotes()
        is TaskDetailAction.HintClick -> showHint()
        is TaskDetailAction.AbilityClick -> emit(TaskDetailNavigation.NavigateToAbilityDetail(action.value))
    }

    fun dispatch(action: CreateNewNoteAction) = dispatchCreateNewNoteAction(action)

    private fun dispatchCreateNewNoteAction(action: CreateNewNoteAction) {
        createNewNoteViewModel.value.dispatch(action)
    }

    private fun showHint(firstTime: Boolean = false) {
        if (firstTime) {
            launchJob {
                if (taskDetailRepository.shouldShowHint()) {
                    UIHint.DiaryTaskDetail.send()
                }
            }
        } else UIHint.DiaryTaskDetail.send()
    }

    private fun refreshUserNotes() {
        _notesPagingConfig.page = 0
        _notesPagingConfig.lastPageReached = false

        val notesViewState = viewState.value.userNotesViewState.copy(items = emptyList())
        emit(viewState.value.copy(userNotesViewState = notesViewState))

        fetchUserNotes()
    }

    private fun fetchAreas() {
        launchJob {
            areasRepository.fetchUserAreas() collectAndProcess {
                onSuccess { list ->
                    list?.let {
                        createNewNoteViewModel.value.dispatch(
                            CreateNewNoteAction.InitAvailableAreas(it)
                        )
                    }
                }
            }
        }
    }

    private fun fetchTasks() {
        launchJob {
            tasksRepository.getCurrentList(forceUpdate = false) collectAndProcess {
                onSuccess { list ->
                    list?.let {
                        createNewNoteViewModel.value.dispatch(
                            CreateNewNoteAction.InitTasksList(list)
                        )
                    }
                }
            }
        }
    }

    private fun fetchUserNotes() {
        if (_notesPagingConfig.lastPageReached || notesLoadingJob?.isActive == true) return

        notesLoadingJob?.cancel()
        notesLoadingJob = launchJob {
            viewState.value.task?.id?.let { taskId ->
                notesRepository.fetchNotesByTask(
                    taskId = taskId,
                    page = _notesPagingConfig.page,
                    perPage = _notesPagingConfig.pageSize
                ) collectAndProcess {
                    isLoading {
                        val notesViewState =
                            viewState.value.userNotesViewState.copy(isLoading = true)
                        emit(viewState.value.copy(userNotesViewState = notesViewState))
                    }
                    onSuccess { list ->
                        _notesPagingConfig.lastPageReached = list.isNullOrEmpty()
                        _notesPagingConfig.page++

                        list?.let {
                            val allItems = viewState.value.userNotesViewState.items.plus(list)
                            val notesViewState = viewState.value.userNotesViewState.copy(
                                isLoading = false,
                                items = allItems
                            )
                            emit(viewState.value.copy(userNotesViewState = notesViewState))
                        }
                    }
                }
            }
        }
    }

    private fun obtainCreateGoal() {
        dispatchCreateNewNoteAction(CreateNewNoteAction.OpenGoalWithTask(viewState.value.task!!))
    }

    private fun obtainCreateDefaultNote() {
        dispatchCreateNewNoteAction(CreateNewNoteAction.OpenDefaultWithTask(viewState.value.task!!))
    }

    private fun obtainAreaChanged() {
        launchJob {
            viewState.value.area?.let { area ->
                areasRepository.fetchAreaDetails(area.id) collectAndProcess {
                    onSuccess {
                        emit(viewState.value.copy(area = it))
                    }
                }
            }
        }
    }

    private fun obtainFavoriteClick() {
        launchJob {
            val taskId = viewState.value.task!!.id!!
            val request =
                if (viewState.value.task!!.isFavorite) tasksRepository.removeFromFavorite(taskId)
                else tasksRepository.addToFavorite(taskId)

            request collectAndProcess {
                isLoading {
                    val task = viewState.value.task?.copy(isFavoriteLoading = it)
                    emit(viewState.value.copy(task = task))
                }
                onSuccess {
                    val updatedFavorite = !viewState.value.task!!.isFavorite
                    val task = viewState.value.task?.copy(isFavorite = updatedFavorite)
                    emit(viewState.value.copy(task = task))
                }
            }
        }
    }

    private fun obtainChangeNotInteresting() {
        if (_changeNotInterestingJob?.isActive == true) return

        _changeNotInterestingJob = launchJob {
            val state = viewState.value.task!!.isNotInteresting
            val taskId = viewState.value.task!!.id!!
            val request = if (state) tasksRepository.removeFromNotInteresting(taskId)
            else tasksRepository.addToNotInteresting(taskId)

            request collectAndProcess {
                onSuccess {
                    _changeNotInterestingJob = null
                    emit(viewState.value.copy(task = it))
                }
            }
        }
    }

    private fun obtainChangeCompleted() {
        if (_changeCompletedJob?.isActive == true) return

        _changeCompletedJob = launchJob {
            val state = viewState.value.task!!.isCompleted
            val taskId = viewState.value.task!!.id!!
            val request = if (state) tasksRepository.removeFromCompleted(taskId)
            else tasksRepository.addToCompleted(taskId)

            request collectAndProcess {
                onSuccess {
                    _changeCompletedJob = null
                    emit(viewState.value.copy(task = it))
                }
            }
        }
    }

}