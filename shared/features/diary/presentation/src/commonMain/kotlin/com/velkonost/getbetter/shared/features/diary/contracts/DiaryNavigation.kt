package com.velkonost.getbetter.shared.features.diary.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface DiaryNavigation : UIContract.Navigation

data object NavigateToAddArea : DiaryNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
        route = NavigationScreen.AddAreaNavScreen.route
    )
}