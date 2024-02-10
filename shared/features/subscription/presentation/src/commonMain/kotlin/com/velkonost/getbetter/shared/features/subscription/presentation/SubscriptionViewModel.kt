package com.velkonost.getbetter.shared.features.subscription.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionAction
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionEvent
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionNavigation
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionViewState
import com.velkonost.getbetter.shared.features.subscription.presentation.model.SubscriptionType

class SubscriptionViewModel
internal constructor(
    private val subscriptionRepository: SubscriptionRepository,
) : BaseViewModel<SubscriptionViewState, SubscriptionAction, SubscriptionNavigation, SubscriptionEvent>(
    initialState = SubscriptionViewState()
) {

    override fun dispatch(action: SubscriptionAction) = when (action) {
        is SubscriptionAction.NavigateBack -> emit(action)
        is SubscriptionAction.SubscriptionItemClick -> obtainSubscriptionItemClick(action.value)
    }

    private fun obtainSubscriptionItemClick(value: SubscriptionType) {
        emit(
            viewState.value.copy(
                selectedItem = value
            )
        )
    }
}