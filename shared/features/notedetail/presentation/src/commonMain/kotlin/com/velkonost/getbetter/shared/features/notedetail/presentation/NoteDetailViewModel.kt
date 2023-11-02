package com.velkonost.getbetter.shared.features.notedetail.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailAction
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailEvent
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailNavigation
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailViewState
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository

class NoteDetailViewModel
internal constructor(
    private val notesRepository: NotesRepository
) : BaseViewModel<NoteDetailViewState, NoteDetailAction, NoteDetailNavigation, NoteDetailEvent>(
    initialState = NoteDetailViewState()
) {
    override fun dispatch(action: NoteDetailAction) = when (action) {
        else -> {}
    }

}