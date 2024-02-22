package com.velkonost.getbetter.shared.features.subscription.presentation

import com.velkonost.getbetter.shared.core.model.subscription.SubscriptionType
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import com.velkonost.getbetter.shared.features.subscription.domain.CheckSubscriptionUseCase
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionAction
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionEvent
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionNavigation
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionViewState

class SubscriptionViewModel
internal constructor(
    private val subscriptionRepository: SubscriptionRepository,
    private val checkSubscriptionUseCase: CheckSubscriptionUseCase
) : BaseViewModel<SubscriptionViewState, SubscriptionAction, SubscriptionNavigation, SubscriptionEvent>(
    initialState = SubscriptionViewState()
) {

    init {
        checkSubscription()
    }

    override fun dispatch(action: SubscriptionAction) = when (action) {
        is SubscriptionAction.NavigateBack -> emit(action)
        is SubscriptionAction.CancelAutoRenewalClick -> obtainCancelAutoRenewal()
        is SubscriptionAction.SubscriptionPurchaseProcessEnded -> checkSubscription()
        is SubscriptionAction.SubscriptionPurchaseClick -> obtainSubscriptionPurchase()
        is SubscriptionAction.SubscriptionItemClick -> obtainSubscriptionItemClick(action.value)
    }

    private fun checkSubscription() {
        launchJob {
            checkSubscriptionUseCase() collectAndProcess {
                onSuccess { subscription ->
                    subscription?.let {
                        emit(viewState.value.copy(subscription = it))
                    }
                }
            }
        }
    }

    private fun obtainSubscriptionPurchase() {
        launchJob {
            val subscriptionType = viewState.value.selectedItem.responseName
            subscriptionRepository.createSubscription(subscriptionType) collectAndProcess {
                isLoading {

                }

                onSuccess {
                    emit(viewState.value.copy(paymentUrl = it?.url))
                }
            }
        }
    }

    private fun obtainCancelAutoRenewal() {
        launchJob {
            subscriptionRepository.cancelAutoRenewal() collectAndProcess {
                isLoading {

                }

                onSuccess { subscription ->
                    subscription?.let {
                        emit(viewState.value.copy(subscription = it))
                    }
                }
            }
        }
    }

    private fun obtainSubscriptionItemClick(value: SubscriptionType) {
        emit(viewState.value.copy(selectedItem = value))
    }
}