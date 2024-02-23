package com.velkonost.getbetter.shared.features.subscription.domain

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckSubscriptionAvailableUseCase(
    private val authRepository: AuthRepository<String>,
    private val subscriptionRepository: SubscriptionRepository
) {

    suspend operator fun invoke(): Flow<ResultState<Boolean>> {
        val isAnonymous = !authRepository.isUserLoggedIn()

        return if (isAnonymous) {
            flow { emit(ResultState.Success(false)) }
        } else {
            subscriptionRepository.isServiceAvailable()
        }
    }

}