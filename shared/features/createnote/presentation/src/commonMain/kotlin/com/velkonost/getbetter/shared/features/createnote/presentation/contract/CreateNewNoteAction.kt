package com.velkonost.getbetter.shared.features.createnote.presentation.contract

import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract


sealed interface CreateNewNoteAction : UIContract.Action {

    data class InitAvailableAreas(val value: List<Area>) : CreateNewNoteAction

    data class InitTasksList(val value: List<TaskUI>) : CreateNewNoteAction

    data object OpenDefault : CreateNewNoteAction

    data object OpenGoal : CreateNewNoteAction

    data class AreaSelect(val value: Area) : CreateNewNoteAction

    data class TaskSelect(val value: TaskUI?) : CreateNewNoteAction

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