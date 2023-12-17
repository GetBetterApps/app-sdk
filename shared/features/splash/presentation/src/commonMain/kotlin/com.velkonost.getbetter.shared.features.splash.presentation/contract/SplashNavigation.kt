package com.velkonost.getbetter.shared.features.splash.presentation.contract

import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_IDENTIFY_ANONYMOUS
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface SplashNavigation : UIContract.Navigation

data object NavigateToAuth : SplashNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
        route = NavigationScreen.AuthNavScreen.route,
        popUpTo = NavigationScreen.SplashNavScreen.route,
        args = hashMapOf(
            Pair(ARG_IDENTIFY_ANONYMOUS, false.encodeToString())
        )
    )
}

data object NavigateToMainFlow : SplashNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
        route = NavigationScreen.SocialNavScreen.route,
        popUpTo = NavigationScreen.SplashNavScreen.route,
        rootRoute = true
    )
}

data object NavigateToOnboarding : SplashNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
        route = NavigationScreen.OnboardingNavScreen.route,
        popUpTo = NavigationScreen.SplashNavScreen.route,
        rootRoute = false
    )
}