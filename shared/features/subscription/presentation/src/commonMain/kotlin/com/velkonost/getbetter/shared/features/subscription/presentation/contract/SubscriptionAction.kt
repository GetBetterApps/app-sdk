package com.velkonost.getbetter.shared.features.subscription.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.features.subscription.presentation.model.SubscriptionType

sealed interface SubscriptionAction : UIContract.Action {

    data class SubscriptionItemClick(val value: SubscriptionType) : SubscriptionAction

    data object NavigateBack : SubscriptionAction, SubscriptionNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateUp()
    }
}