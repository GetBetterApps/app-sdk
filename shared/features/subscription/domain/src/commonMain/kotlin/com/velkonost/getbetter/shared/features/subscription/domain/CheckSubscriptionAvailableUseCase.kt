package com.velkonost.getbetter.shared.features.subscription.domain

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import kotlinx.coroutines.flow.Flow

class CheckSubscriptionAvailableUseCase(
    private val subscriptionRepository: SubscriptionRepository
) {

    suspend operator fun invoke(): Flow<ResultState<Boolean>> =
        subscriptionRepository.isServiceAvailable()

}