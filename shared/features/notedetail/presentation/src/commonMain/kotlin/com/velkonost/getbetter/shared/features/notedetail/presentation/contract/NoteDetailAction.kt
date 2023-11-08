package com.velkonost.getbetter.shared.features.notedetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface NoteDetailAction : UIContract.Action {
    data object AreaChanged : NoteDetailAction

    data class TextChanged(val value: String) : NoteDetailAction

    data class SetCompletionDate(val value: Long?) : NoteDetailAction

    data class NewTagTextChanged(val value: String) : NoteDetailAction

    data object AddNewTag : NoteDetailAction

    data class RemoveTag(val value: String) : NoteDetailAction

    data class NewSubNoteTextChanged(val value: String) : NoteDetailAction

    data object AddSubNote : NoteDetailAction

    data class RemoveSubNote(val value: SubNoteUI) : NoteDetailAction

    data object CompleteClick : NoteDetailAction

    data class CompleteSubNoteClick(val value: SubNoteUI) : NoteDetailAction

    data object UnCompleteClick : NoteDetailAction

    data class UnCompleteSubNoteClick(val value: SubNoteUI) : NoteDetailAction

    data object StartEditClick : NoteDetailAction

    data object CancelEditClick : NoteDetailAction

    data object EndEditClick : NoteDetailAction

    data object DeleteClick : NoteDetailAction

    data object AuthorClick : NoteDetailAction

    data object LikeClick : NoteDetailAction
}

data object NavigateBack : NoteDetailAction, NoteDetailNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateUp()
}



