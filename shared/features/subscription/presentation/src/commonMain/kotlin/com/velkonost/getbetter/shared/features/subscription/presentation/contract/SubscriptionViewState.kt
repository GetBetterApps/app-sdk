package com.velkonost.getbetter.shared.features.subscription.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.subscription.presentation.model.SubscriptionType

data class SubscriptionViewState(
    val isLoading: Boolean = false,
    val items: List<SubscriptionType> = SubscriptionType.entries,
    val selectedItem: SubscriptionType = SubscriptionType.Year
) : UIContract.State