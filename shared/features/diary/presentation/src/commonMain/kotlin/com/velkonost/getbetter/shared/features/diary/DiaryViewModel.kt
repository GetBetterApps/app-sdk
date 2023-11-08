package com.velkonost.getbetter.shared.features.diary

import AreasRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.likes.LikeType
import com.velkonost.getbetter.shared.core.model.likes.LikesData
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.diary.api.DiaryRepository
import com.velkonost.getbetter.shared.features.diary.contracts.AddAreaClick
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryAction
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryEvent
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryNavigation
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryViewState
import com.velkonost.getbetter.shared.features.diary.contracts.NavigateToAddArea
import com.velkonost.getbetter.shared.features.diary.contracts.NavigateToNoteDetail
import com.velkonost.getbetter.shared.features.diary.contracts.NoteClick
import com.velkonost.getbetter.shared.features.diary.contracts.NoteLikeClick
import com.velkonost.getbetter.shared.features.likes.api.LikesRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DiaryViewModel
internal constructor(
    private val areasRepository: AreasRepository,
    private val notesRepository: NotesRepository,
    private val diaryRepository: DiaryRepository,
    private val likesRepository: LikesRepository
) : BaseViewModel<DiaryViewState, DiaryAction, DiaryNavigation, DiaryEvent>(
    initialState = DiaryViewState()
) {

    private val _notesPagingConfig = PagingConfig()
    private var notesLoadingJob: Job? = null

    private val likesJobsMap: HashMap<Int, Job> = hashMapOf()

    fun refreshData() {
        fetchAreas()

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
        is DiaryAction.NotesLoadNextPage -> fetchNotes()
    }

    private fun obtainNoteLikeClick(value: Note) {
        if (likesJobsMap.containsKey(value.id)) return

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
            likesJobsMap[value.id] = it
        }.invokeOnCompletion {
            likesJobsMap.remove(value.id)
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