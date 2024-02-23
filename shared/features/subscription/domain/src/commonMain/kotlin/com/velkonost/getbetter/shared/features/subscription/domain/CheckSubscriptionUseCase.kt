package com.velkonost.getbetter.shared.features.subscription.domain

import com.velkonost.getbetter.shared.core.model.subscription.Subscription
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

class CheckSubscriptionUseCase(
    private val checkSubscriptionAvailableUseCase: CheckSubscriptionAvailableUseCase,
    private val subscriptionRepository: SubscriptionRepository
) {

    suspend operator fun invoke(): Flow<ResultState<Subscription>> =
        checkSubscriptionAvailableUseCase().flatMapMerge { availabilityResult ->
            when (availabilityResult) {

                is ResultState.Loading -> {
                    flow { emit(ResultState.Success(Subscription.NoSubscription)) }
                }

                is ResultState.Success -> {
                    if (availabilityResult.data) {
                        subscriptionRepository.fetchSubscriptionStatus()
                    } else {
                        flow { emit(ResultState.Success(Subscription.NoSubscription)) }
                    }
                }

                is ResultState.Failure -> {
                    flow { emit(ResultState.Success(Subscription.NoSubscription)) }
                }
            }
        }
}