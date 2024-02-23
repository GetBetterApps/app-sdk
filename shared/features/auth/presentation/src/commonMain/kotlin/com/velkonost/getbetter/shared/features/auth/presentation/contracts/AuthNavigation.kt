package com.velkonost.getbetter.shared.features.auth.presentation.contracts

import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_RETURN_TO_PROFILE
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface AuthNavigation : UIContract.Navigation

data object NavigateToMainFlow : AuthNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
        route = NavigationScreen.SocialNavScreen.route,
        rootRoute = true,
        popUpToStart = true,
        popUpTo = NavigationScreen.AuthNavScreen.route,
    )
}

data object NavigateToPaywall : AuthNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
        route = NavigationScreen.SubscriptionNavScreen.route,
        rootRoute = false,
        popUpToStart = true,
        popUpTo = NavigationScreen.AuthNavScreen.route,
        args = hashMapOf(
            Pair(ARG_RETURN_TO_PROFILE, true.encodeToString())
        )
    )
}