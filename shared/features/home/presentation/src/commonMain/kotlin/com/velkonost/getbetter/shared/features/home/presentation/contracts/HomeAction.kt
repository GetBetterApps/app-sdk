package com.velkonost.getbetter.shared.features.home.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface HomeAction : UIContract.Action

data object NavigateToDetails : HomeAction, HomeNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
        route = NavigationScreen.DetailNavScreen.route,
    )
}