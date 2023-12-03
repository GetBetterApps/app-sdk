package com.velkonost.getbetter.shared.features.taskdetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface TaskDetailAction : UIContract.Action {

    data object AreaChanged : TaskDetailAction

    data object NavigateBack : TaskDetailAction, TaskDetailNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateUp()
    }
}