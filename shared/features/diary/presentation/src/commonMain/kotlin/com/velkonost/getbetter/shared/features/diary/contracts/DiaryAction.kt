package com.velkonost.getbetter.shared.features.diary.contracts

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import model.Area

sealed interface DiaryAction : UIContract.Action

data object AddAreaClick : DiaryAction

sealed interface CreateNewAreaAction : DiaryAction {
    data object Open : CreateNewAreaAction
    data class EmojiSelect(val value: Emoji) : CreateNewAreaAction
    data class NameChanged(val value: String) : CreateNewAreaAction
    data class DescriptionChanged(val value: String) : CreateNewAreaAction
    data class RequiredLevelChanged(val value: Int) : CreateNewAreaAction
    data object PrivateChanged : CreateNewAreaAction
    data object CreateClick : CreateNewAreaAction
}

sealed interface CreateNewNoteAction : DiaryAction {
    data object OpenDefault : CreateNewNoteAction

    data object OpenGoal : CreateNewNoteAction

    data class AreaSelect(val value: Area) : CreateNewNoteAction

    data class TextChanged(val value: String) : CreateNewNoteAction

    data class NewTagTextChanged(val value: String) : CreateNewNoteAction

    data object AddNewTag : CreateNewNoteAction

    data class RemoveTag(val value: String) : CreateNewNoteAction

    data class NewSubNoteTextChanged(val value: String) : CreateNewNoteAction

    data object AddSubNote : CreateNewNoteAction

    data class RemoveSubNote(val value: String) : CreateNewNoteAction

    data class AddImageUrl(val value: String) : CreateNewNoteAction

    data class RemoveImageUrl(val value: String) : CreateNewNoteAction

    data object CloseBecauseZeroAreas : CreateNewNoteAction
}