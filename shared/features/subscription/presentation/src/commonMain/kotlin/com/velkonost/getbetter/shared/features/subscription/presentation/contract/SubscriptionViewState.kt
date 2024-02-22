package com.velkonost.getbetter.shared.features.subscription.presentation.contract

import com.velkonost.getbetter.shared.core.model.subscription.Subscription
import com.velkonost.getbetter.shared.core.model.subscription.SubscriptionType
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class SubscriptionViewState(
    val isLoading: Boolean = false,
    val items: List<SubscriptionType> = SubscriptionType.entries,
    val selectedItem: SubscriptionType = SubscriptionType.Year,
    val paymentUrl: String? = null,
    val subscription: Subscription = Subscription.NoSubscription
) : UIContract.State