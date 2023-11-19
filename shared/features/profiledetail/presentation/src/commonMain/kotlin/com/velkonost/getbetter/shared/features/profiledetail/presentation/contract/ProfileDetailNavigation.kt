package com.velkonost.getbetter.shared.features.profiledetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.withoutAuthorData
import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_NOTE
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface ProfileDetailNavigation : UIContract.Navigation {
    data class NavigateToNoteDetail(val note: Note) : ProfileDetailNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
            route = NavigationScreen.NoteDetailNavScreen.route,
            args = hashMapOf(
                Pair(ARG_NOTE, note.withoutAuthorData().encodeToString())
            )
        )
    }
}