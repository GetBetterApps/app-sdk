package com.velkonost.getbetter.shared.features.taskdetail.presentation

import AreasRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.createnote.presentation.CreateNewNoteViewModel
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteEvent
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
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
    private val areasRepository: AreasRepository
) : BaseViewModel<TaskDetailViewState, TaskDetailAction, TaskDetailNavigation, TaskDetailEvent>(
    initialState = TaskDetailViewState(),
    savedStateHandle = savedStateHandle
) {

    private val task = savedStateHandle.task.stateInWhileSubscribed(initialValue = null)

    private var _changeNotInterestingJob: Job? = null
    private var _changeCompletedJob: Job? = null

    init {
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
        MutableStateFlow(CreateNewNoteViewModel(notesRepository, null))

    override fun dispatch(action: TaskDetailAction) = when (action) {
        is TaskDetailAction.NavigateBack -> emit(action)
        is TaskDetailAction.AreaChanged -> obtainAreaChanged()
        is TaskDetailAction.FavoriteClick -> obtainFavoriteClick()
        is TaskDetailAction.NotInterestingClick -> obtainChangeNotInteresting()
        is TaskDetailAction.CompletedClick -> obtainChangeCompleted()
    }

    fun dispatch(action: CreateNewNoteAction) = dispatchCreateNewNoteAction(action)

    private fun dispatchCreateNewNoteAction(action: CreateNewNoteAction) {
        createNewNoteViewModel.value.dispatch(action)
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