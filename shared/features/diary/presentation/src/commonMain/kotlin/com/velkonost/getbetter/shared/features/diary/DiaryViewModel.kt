package com.velkonost.getbetter.shared.features.diary

import AreasRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.features.diary.contracts.AddAreaClick
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryAction
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryEvent
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryNavigation
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryViewState
import com.velkonost.getbetter.shared.features.diary.contracts.NavigateToAddArea
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class DiaryViewModel
internal constructor(
    private val areasRepository: AreasRepository,
    private val notesRepository: NotesRepository
) : BaseViewModel<DiaryViewState, DiaryAction, DiaryNavigation, DiaryEvent>(
    initialState = DiaryViewState()
) {

    private val _notesPagingConfig = PagingConfig()

    fun refreshData() {
        fetchAreas()
        fetchNotes()
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
        MutableStateFlow(CreateNewNoteViewModel(notesRepository))

    override fun dispatch(action: DiaryAction) = when (action) {
        is CreateNewAreaAction -> dispatchCreateNewAreaAction(action)
        is CreateNewNoteAction -> dispatchCreateNewNoteAction(action)
        is AddAreaClick -> emit(NavigateToAddArea)
    }

    private fun dispatchCreateNewAreaAction(action: CreateNewAreaAction) {
        createNewAreaViewModel.value.dispatch(action)
    }

    private fun dispatchCreateNewNoteAction(action: CreateNewNoteAction) {
        createNewNoteViewModel.value.dispatch(action)
    }

    private fun fetchAreas() {
        launchJob {
            areasRepository.fetchUserAreas()
                .collectLatest { result ->
                    with(result) {
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

                                emit(
                                    viewState.value.copy(areasViewState = areasViewState)
                                )

                                createNewNoteViewModel.value.dispatch(
                                    CreateNewNoteAction.InitAvailableAreas(it)
                                )

                            }

                        }
                        onFailureWithMsg { _, message ->
                            message?.let { emit(it) }
                        }
                    }
                }
        }
    }

    private fun fetchNotes() {
        if (_notesPagingConfig.lastPageReached) return

        launchJob {
            notesRepository.fetchUserDetails(
                page = _notesPagingConfig.page,
                perPage = _notesPagingConfig.pageSize
            ).collect { result ->
                with(result) {
                    isLoading {
                        val notesViewState = viewState.value.notesViewState.copy(isLoading = it)
                        emit(viewState.value.copy(notesViewState = notesViewState))
                    }
                    onSuccess { items ->
                        _notesPagingConfig.lastPageReached = items.isNullOrEmpty()
                        _notesPagingConfig.page++

                        items?.let {

                        }
                    }
                    onFailureWithMsg { _, message ->
                        message?.let { emit(it) }
                    }
                }
            }
        }
    }
}