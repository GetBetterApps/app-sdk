package com.velkonost.getbetter.shared.features.subscription.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class SubscriptionViewState(
    val isLoading: Boolean = false

) : UIContract.State