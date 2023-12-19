package com.velkonost.getbetter.shared.features.onboarding.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface OnboardingNavigation : UIContract.Navigation {
    data object NavigateToMainFlow : OnboardingNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
            route = NavigationScreen.SocialNavScreen.route,
            rootRoute = true,
            popUpToStart = true,
            popUpTo = NavigationScreen.OnboardingNavScreen.route,
        )
    }
}