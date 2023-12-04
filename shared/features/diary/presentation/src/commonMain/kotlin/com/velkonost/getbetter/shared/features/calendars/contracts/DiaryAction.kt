package com.velkonost.getbetter.shared.features.calendars.contracts

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface DiaryAction : UIContract.Action {
    data object NotesLoadNextPage : DiaryAction

    data class TaskFavoriteClick(val value: TaskUI) : DiaryAction

    data object TasksListUpdateClick : DiaryAction
}

data object AddAreaClick : DiaryAction

data class NoteClick(val value: Note) : DiaryAction

data class TaskClick(val value: TaskUI) : DiaryAction

data class NoteLikeClick(val value: Note) : DiaryAction

data class AreaLikeClick(val value: Area) : DiaryAction

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

    data class InitAvailableAreas(val value: List<Area>) : CreateNewNoteAction

    data object OpenDefault : CreateNewNoteAction

    data object OpenGoal : CreateNewNoteAction

    data class AreaSelect(val value: Area) : CreateNewNoteAction

    data class TextChanged(val value: String) : CreateNewNoteAction

    data class NewTagTextChanged(val value: String) : CreateNewNoteAction

    data object AddNewTag : CreateNewNoteAction

    data class RemoveTag(val value: String) : CreateNewNoteAction

    data class NewSubNoteTextChanged(val value: String) : CreateNewNoteAction

    data object AddSubNote : CreateNewNoteAction

    data class RemoveSubNote(val value: SubNoteUI) : CreateNewNoteAction

    data class AddImageUrl(val value: String) : CreateNewNoteAction

    data class RemoveImageUrl(val value: String) : CreateNewNoteAction

    data object PrivateChanged : CreateNewNoteAction

    data class SetCompletionDate(val value: Long?) : CreateNewNoteAction

    data object CloseBecauseZeroAreas : CreateNewNoteAction

    data object CreateClick : CreateNewNoteAction
}