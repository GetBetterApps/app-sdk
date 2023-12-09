package com.velkonost.getbetter.shared.features.calendars.presentation.contracts

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.withoutAuthorData
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_NOTE
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_TASK
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface CalendarsNavigation : UIContract.Navigation {
    data class NavigateToNoteDetail(val note: Note) : CalendarsNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
            route = NavigationScreen.NoteDetailNavScreen.route,
            args = hashMapOf(
                Pair(ARG_NOTE, note.withoutAuthorData().encodeToString())
            )
        )
    }

    data class NavigateToTaskDetail(val task: TaskUI) : CalendarsNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
            route = NavigationScreen.TaskDetailNavScreen.route,
            args = hashMapOf(
                Pair(ARG_TASK, task.encodeToString())
            )
        )
    }
}