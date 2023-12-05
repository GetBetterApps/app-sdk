package com.velkonost.getbetter.shared.features.notedetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_TASK
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface NoteDetailNavigation : UIContract.Navigation {
    data class NavigateToTaskDetail(val task: TaskUI) : NoteDetailNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
            route = NavigationScreen.TaskDetailNavScreen.route,
            args = hashMapOf(
                Pair(ARG_TASK, task.encodeToString())
            )
        )
    }
}