package com.velkonost.getbetter.shared.features.calendars

import AreasRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.likes.LikeType
import com.velkonost.getbetter.shared.core.model.likes.LikesData
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.calendars.api.DiaryRepository
import com.velkonost.getbetter.shared.features.calendars.contracts.AddAreaClick
import com.velkonost.getbetter.shared.features.calendars.contracts.AreaLikeClick
import com.velkonost.getbetter.shared.features.calendars.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.calendars.contracts.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.calendars.contracts.DiaryAction
import com.velkonost.getbetter.shared.features.calendars.contracts.DiaryEvent
import com.velkonost.getbetter.shared.features.calendars.contracts.DiaryNavigation
import com.velkonost.getbetter.shared.features.calendars.contracts.DiaryViewState
import com.velkonost.getbetter.shared.features.calendars.contracts.NavigateToAddArea
import com.velkonost.getbetter.shared.features.calendars.contracts.NavigateToNoteDetail
import com.velkonost.getbetter.shared.features.calendars.contracts.NoteClick
import com.velkonost.getbetter.shared.features.calendars.contracts.NoteLikeClick
import com.velkonost.getbetter.shared.features.likes.api.LikesRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.tasks.api.TasksRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DiaryViewModel
internal constructor(
    private val areasRepository: AreasRepository,
    private val notesRepository: NotesRepository,
    private val tasksRepository: TasksRepository,
    private val diaryRepository: DiaryRepository,
    private val likesRepository: LikesRepository
) : BaseViewModel<DiaryViewState, DiaryAction, DiaryNavigation, DiaryEvent>(
    initialState = DiaryViewState()
) {

    private val _notesPagingConfig = PagingConfig()
    private var notesLoadingJob: Job? = null

    private val notesLikesJobsMap: HashMap<Int, Job> = hashMapOf()
    private val areasLikesJobsMap: HashMap<Int, Job> = hashMapOf()

    fun refreshData() {
        fetchAreas()
        fetchTasks()

        launchJob {
            if (diaryRepository.checkNeedsResetState()) {
                refreshNotesState()
            } else {
                checkUpdatedNote()
            }

            fetchNotes()
        }
    }

    override fun init() {
        launchJob {
            createNewAreaViewModel.value.viewState.collect {
                emit(viewState.value.copy(createNewAreaViewState = it))
            }
        }

        launchJob {
            createNewAreaViewModel.value.events.collect {
                emit(it)
            }
        }

        launchJob {
            createNewNoteViewModel.value.viewState.collect {
                emit(viewState.value.copy(createNewNoteViewState = it))
            }
        }

        launchJob {
            createNewNoteViewModel.value.events.collect {
                emit(it)
            }
        }
    }

    @NativeCoroutinesState
    val createNewAreaViewModel: StateFlow<CreateNewAreaViewModel> =
        MutableStateFlow(CreateNewAreaViewModel(areasRepository))

    @NativeCoroutinesState
    val createNewNoteViewModel: StateFlow<CreateNewNoteViewModel> =
        MutableStateFlow(CreateNewNoteViewModel(notesRepository, diaryRepository))

    override fun dispatch(action: DiaryAction) = when (action) {
        is CreateNewAreaAction -> dispatchCreateNewAreaAction(action)
        is CreateNewNoteAction -> dispatchCreateNewNoteAction(action)
        is AddAreaClick -> emit(NavigateToAddArea)
        is NoteClick -> obtainNoteClick(action.value)
        is NoteLikeClick -> obtainNoteLikeClick(action.value)
        is AreaLikeClick -> obtainAreaLikeClick(action.value)
        is DiaryAction.NotesLoadNextPage -> fetchNotes()
    }

    private fun obtainNoteLikeClick(value: Note) {
        if (notesLikesJobsMap.containsKey(value.id)) return

        launchJob {
            val likeType = when (value.likesData.userLike) {
                LikeType.Positive -> LikeType.None
                else -> LikeType.Positive
            }

            likesRepository.addLike(
                entityType = EntityType.Note,
                entityId = value.id,
                likeType = likeType
            ) collectAndProcess {
                isLoading {
                    val itemLikesData = value.likesData.copy(isLikesLoading = true)

                    val indexOfChangedItem =
                        viewState.value.notesViewState.items.indexOfFirst { item -> item.id == value.id }

                    val allItems = viewState.value.notesViewState.items.toMutableList()

                    if (indexOfChangedItem != -1) {
                        allItems[indexOfChangedItem] = value.copy(likesData = itemLikesData)
                    }

                    val notesViewState = viewState.value.notesViewState.copy(items = allItems)
                    emit(viewState.value.copy(notesViewState = notesViewState))
                }
                onSuccess { entityLikes ->
                    entityLikes?.let {
                        val itemLikesData =
                            LikesData(
                                totalLikes = it.total,
                                userLike = it.userLikeType
                            )

                        val indexOfChangedItem =
                            viewState.value.notesViewState.items.indexOfFirst { item -> item.id == value.id }

                        val allItems = viewState.value.notesViewState.items.toMutableList()

                        if (indexOfChangedItem != -1) {
                            allItems[indexOfChangedItem] = value.copy(likesData = itemLikesData)
                        }

                        val notesViewState =
                            viewState.value.notesViewState.copy(items = allItems)
                        emit(viewState.value.copy(notesViewState = notesViewState))
                    }
                }
            }
        }.also {
            notesLikesJobsMap[value.id] = it
        }.invokeOnCompletion {
            notesLikesJobsMap.remove(value.id)
        }
    }

    private fun obtainAreaLikeClick(value: Area) {
        if (areasLikesJobsMap.containsKey(value.id)) return

        launchJob {
            val likeType = when (value.likesData.userLike) {
                LikeType.Positive -> LikeType.None
                else -> LikeType.Positive
            }

            likesRepository.addLike(
                entityType = EntityType.Area,
                entityId = value.id,
                likeType = likeType
            ) collectAndProcess {
                isLoading {
                    val itemLikesData = value.likesData.copy(isLikesLoading = true)

                    val indexOfChangedItem =
                        viewState.value.areasViewState.items.indexOfFirst { item -> item.id == value.id }

                    val allItems = viewState.value.areasViewState.items.toMutableList()

                    if (indexOfChangedItem != -1) {
                        allItems[indexOfChangedItem] = value.copy(likesData = itemLikesData)
                    }

                    val areasViewState = viewState.value.areasViewState.copy(items = allItems)
                    emit(viewState.value.copy(areasViewState = areasViewState))
                }
                onSuccess { entityLikes ->
                    entityLikes?.let {
                        val itemLikesData =
                            LikesData(
                                totalLikes = it.total,
                                userLike = it.userLikeType
                            )

                        val indexOfChangedItem =
                            viewState.value.areasViewState.items.indexOfFirst { item -> item.id == value.id }

                        val allItems = viewState.value.areasViewState.items.toMutableList()

                        if (indexOfChangedItem != -1) {
                            allItems[indexOfChangedItem] = value.copy(likesData = itemLikesData)
                        }

                        val areasViewState =
                            viewState.value.areasViewState.copy(items = allItems)
                        emit(viewState.value.copy(areasViewState = areasViewState))
                    }
                }
            }
        }
    }

    private fun refreshNotesState() {
        _notesPagingConfig.page = 0
        _notesPagingConfig.lastPageReached = false
        val notesViewState = viewState.value.notesViewState.copy(
            isLoading = true,
            items = emptyList()
        )
        emit(viewState.value.copy(notesViewState = notesViewState))
    }

    private fun dispatchCreateNewAreaAction(action: CreateNewAreaAction) {
        createNewAreaViewModel.value.dispatch(action)
    }

    private fun dispatchCreateNewNoteAction(action: CreateNewNoteAction) {
        createNewNoteViewModel.value.dispatch(action)
    }

    private fun obtainNoteClick(value: Note) {
        launchJob {
            diaryRepository.saveUpdatedNoteId(value.id)
            emit(NavigateToNoteDetail(value))
        }
    }

    private fun checkUpdatedNote() {
        launchJob {
            diaryRepository.getUpdatedNoteId()
                .collect { updatedNoteResult ->
                    updatedNoteResult.onSuccess { noteId ->
                        noteId?.let { refreshNoteData(it) }
                    }
                }
        }
    }

    private fun refreshNoteData(noteId: Int) {
        launchJob {
            notesRepository.getNoteDetails(noteId) collectAndProcess {
                onSuccess { note ->
                    note?.let {
                        val indexOfChangedItem =
                            viewState.value.notesViewState.items.indexOfFirst { it.id == note.id }
                        val allItems =
                            viewState.value.notesViewState.items.toMutableList()

                        if (indexOfChangedItem == -1) {
                            allItems.add(0, note)
                        } else if (!note.isActive) {
                            allItems.removeAt(indexOfChangedItem)
                        } else {
                            allItems[indexOfChangedItem] = note
                        }

                        allItems.filter { it.area.id == note.area.id }.forEach {
                            it.area = note.area
                        }

                        val notesViewState =
                            viewState.value.notesViewState.copy(
                                isLoading = false,
                                items = allItems
                            )
                        emit(viewState.value.copy(notesViewState = notesViewState))
                    }
                }
            }
        }
    }

    private fun fetchAreas() {
        launchJob {
            areasRepository.fetchUserAreas() collectAndProcess {
                isLoading {
                    if (viewState.value.areasViewState.items.isEmpty()) {
                        val areasViewState =
                            viewState.value.areasViewState.copy(isLoading = it)
                        emit(viewState.value.copy(areasViewState = areasViewState))
                    }
                }
                onSuccess { list ->
                    list?.let {
                        val areasViewState = viewState.value.areasViewState.copy(items = it)
                        emit(viewState.value.copy(areasViewState = areasViewState))

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
            tasksRepository.getCurrentList() collectAndProcess {
                isLoading {
                    val tasksViewState = viewState.value.tasksViewState.copy(isLoading = it)
                    emit(viewState.value.copy(tasksViewState = tasksViewState))
                }
                onSuccess { list ->
                    list?.let {
                        val tasksViewState = viewState.value.tasksViewState.copy(
                            favoriteItems = list.filter { it.isFavorite },
                            currentItems = list.filter { !it.isFavorite }
                        )
                        emit(viewState.value.copy(tasksViewState = tasksViewState))
                    }
                }
            }
        }

        launchJob {
            tasksRepository.getCompletedTasks() collectAndProcess {
                onSuccess { list ->
                    list?.let {
                        val tasksViewState = viewState.value.tasksViewState.copy(
                            completedItems = list
                        )
                        emit(viewState.value.copy(tasksViewState = tasksViewState))
                    }
                }
            }
        }
    }

    private fun fetchNotes() {
        if (_notesPagingConfig.lastPageReached || notesLoadingJob?.isActive == true) return

        notesLoadingJob?.cancel()
        notesLoadingJob = launchJob {
            notesRepository.fetchUserNotes(
                page = _notesPagingConfig.page,
                perPage = _notesPagingConfig.pageSize
            ) collectAndProcess {
                isLoading {
                    val notesViewState = viewState.value.notesViewState.copy(isLoading = true)
                    emit(viewState.value.copy(notesViewState = notesViewState))
                }
                onSuccess { items ->
                    _notesPagingConfig.lastPageReached = items.isNullOrEmpty()
                    _notesPagingConfig.page++

                    items?.let {
                        val allItems = viewState.value.notesViewState.items.plus(it)
                        val notesViewState =
                            viewState.value.notesViewState.copy(
                                isLoading = false,
                                items = allItems
                            )
                        emit(viewState.value.copy(notesViewState = notesViewState))
                    }
                }
            }
        }
    }
}