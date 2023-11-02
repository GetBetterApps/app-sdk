package com.velkonost.getbetter.shared.features.notedetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class NoteDetailViewState(
    val isLoading: Boolean = false,
    val note: Note? = null,
    val newTag: TagUI = TagUI(),
    val newSubNote: SubNoteUI = SubNoteUI(),
    val noteState: State = State.View,

    ) : UIContract.State

enum class State {
    View, Editing
}