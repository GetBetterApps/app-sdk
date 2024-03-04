package com.velkonost.getbetter.shared.features.social.contracts

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.withoutAuthorData
import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_NOTE
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface SocialNavigation : UIContract.Navigation

data class NavigateToNoteDetail(val note: Note) : SocialNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
        route = NavigationScreen.NoteDetailNavScreen.route,
        args = hashMapOf(
            Pair(ARG_NOTE, note.withoutAuthorData().encodeToString())
        )
    )
}

data object NavigateToPaywall : SocialNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
        route = NavigationScreen.SubscriptionNavScreen.route,
    )
}