package com.velkonost.getbetter.shared.features.diary.presentation.contracts

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface DiaryAction : UIContract.Action {
    data object NotesLoadNextPage : DiaryAction

    data class TaskFavoriteClick(val value: TaskUI) : DiaryAction

    data object TasksListUpdateClick : DiaryAction

    data class HintClick(val firstTime: Boolean = false, val index: Int) : DiaryAction

    data object NavigateToPaywallClick : DiaryAction

    data object StartTrialClick : DiaryAction
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

    data object HintClick : CreateNewAreaAction
}
