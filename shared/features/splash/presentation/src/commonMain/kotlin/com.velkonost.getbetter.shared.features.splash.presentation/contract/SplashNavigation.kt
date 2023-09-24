package com.velkonost.getbetter.shared.features.splash.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed class SplashNavigation : UIContract.Navigation

data object NavigateToAuth : SplashNavigation() {
    override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
        route = NavigationScreen.AuthNavScreen.route,
        popUpTo = NavigationScreen.SplashNavScreen.route,
    )
}

data object NavigateToMainFlow : SplashNavigation() {
    override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
        route = NavigationScreen.SocialNavScreen.route,
        popUpTo = NavigationScreen.SplashNavScreen.route,
    )
    override val delay: Long
        get() = 500
}