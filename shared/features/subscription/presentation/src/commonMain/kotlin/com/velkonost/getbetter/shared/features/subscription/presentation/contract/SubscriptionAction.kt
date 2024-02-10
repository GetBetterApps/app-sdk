package com.velkonost.getbetter.shared.features.subscription.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface SubscriptionAction : UIContract.Action {

    data object NavigateBack : SubscriptionAction, SubscriptionNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateUp()
    }
}