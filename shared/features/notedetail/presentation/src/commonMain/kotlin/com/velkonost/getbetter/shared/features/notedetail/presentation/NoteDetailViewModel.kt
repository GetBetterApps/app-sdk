package com.velkonost.getbetter.shared.features.notedetail.presentation

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailAction
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailEvent
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailNavigation
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailViewState
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository

class NoteDetailViewModel
internal constructor(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
) : BaseViewModel<NoteDetailViewState, NoteDetailAction, NoteDetailNavigation, NoteDetailEvent>(
    initialState = NoteDetailViewState(),
    savedStateHandle = savedStateHandle
) {

    @NativeCoroutinesState
    private val note = savedStateHandle.note.stateInWhileSubscribed(initialValue = null)

    init {
        launchJob {
            note.collect {
                emit(viewState.value.copy(note = it))
            }
        }

    }

    override fun dispatch(action: NoteDetailAction) = when (action) {
        is NavigateBack -> emit(action)
        else -> {}
    }

}