package com.velkonost.getbetter.shared.features.profile.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface ProfileNavigation : UIContract.Navigation

data object NavigateToAuth : ProfileNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
        route = NavigationScreen.AuthNavScreen.route,
        popUpToStart = true,
        popUpTo = NavigationScreen.SplashNavScreen.route,
    )
}