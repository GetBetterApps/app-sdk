package com.velkonost.getbetter.shared.features.subscription.presentation.contract

import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_IDENTIFY_ANONYMOUS
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface SubscriptionNavigation : UIContract.Navigation {
    data object NavigateToAuth : SubscriptionNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
            route = NavigationScreen.AuthNavScreen.route,
            popUpToStart = true,
            popUpTo = NavigationScreen.SplashNavScreen.route,
            args = hashMapOf(
                Pair(ARG_IDENTIFY_ANONYMOUS, true.encodeToString())
            )
        )
    }
}