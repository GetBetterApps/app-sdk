package com.velkonost.getbetter.shared.features.subscription.presentation

import com.velkonost.getbetter.shared.core.model.subscription.SubscriptionType
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onFailure
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import com.velkonost.getbetter.shared.features.subscription.domain.CheckSubscriptionUseCase
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionAction
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionEvent
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionNavigation
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import kotlinx.coroutines.Job

class SubscriptionViewModel
internal constructor(
    savedStateHandle: SavedStateHandle,
    private val userInfoRepository: UserInfoRepository,
    private val subscriptionRepository: SubscriptionRepository,
    private val checkSubscriptionUseCase: CheckSubscriptionUseCase
) : BaseViewModel<SubscriptionViewState, SubscriptionAction, SubscriptionNavigation, SubscriptionEvent>(
    initialState = SubscriptionViewState()
) {

    private var _checkSubscriptionJob: Job? = null

    init {
        checkUserInfo {
            checkSubscription()
        }
    }

    override fun dispatch(action: SubscriptionAction) = when (action) {
        is SubscriptionAction.NavigateBack -> emit(action)
        is SubscriptionAction.CancelAutoRenewalClick -> obtainCancelAutoRenewal()
        is SubscriptionAction.SubscriptionPurchaseProcessEnded -> checkSubscription()
        is SubscriptionAction.SubscriptionPurchaseClick -> obtainSubscriptionPurchase()
        is SubscriptionAction.SubscriptionItemClick -> obtainSubscriptionItemClick(action.value)
    }

    private fun checkUserInfo(onSuccess: () -> Unit) {
        launchJob {
            userInfoRepository.fetchInfo() collectAndProcess {
                onSuccess { userInfo ->
                    if (userInfo?.email.isNullOrEmpty()) {
                        emit(SubscriptionNavigation.NavigateToAuth)
                    } else onSuccess()
                }
                onFailure {
                    emit(SubscriptionNavigation.NavigateToAuth)
                }
            }
        }
    }

    private fun checkSubscription() {
        emit(viewState.value.copy(paymentUrl = null))

        if (_checkSubscriptionJob?.isActive == true) return

        _checkSubscriptionJob = launchJob {
            checkSubscriptionUseCase() collectAndProcess {
                onSuccess { subscription ->
                    subscription?.let {
                        emit(viewState.value.copy(subscription = it))
                    }
                }
            }
        }

        _checkSubscriptionJob?.invokeOnCompletion {
            _checkSubscriptionJob = null
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