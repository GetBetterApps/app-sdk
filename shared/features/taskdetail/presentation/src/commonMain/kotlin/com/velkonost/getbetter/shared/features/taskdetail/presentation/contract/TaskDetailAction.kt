package com.velkonost.getbetter.shared.features.taskdetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface TaskDetailAction : UIContract.Action {

    data object AreaChanged : TaskDetailAction

    data object FavoriteClick : TaskDetailAction

    data object NotInterestingClick : TaskDetailAction

    data object CompletedClick : TaskDetailAction

    data object CreateGoalClick : TaskDetailAction

    data object CreateNoteClick : TaskDetailAction

    data object NavigateBack : TaskDetailAction, TaskDetailNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateUp()
    }

    data object UserNotesLoadNextPage : TaskDetailAction

    data object RefreshUserNotes : TaskDetailAction

    data object HintClick : TaskDetailAction

    data class AbilityClick(val value: Ability) : TaskDetailAction
}