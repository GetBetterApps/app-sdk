package com.velkonost.getbetter.shared.features.subscription.api

import com.velkonost.getbetter.shared.core.model.subscription.Subscription
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.subscription.api.model.SubscriptionPayment
import kotlinx.coroutines.flow.Flow

interface SubscriptionRepository {

    fun createSubscription(
        subscriptionType: String
    ): Flow<ResultState<SubscriptionPayment>>

    fun startTrial(): Flow<ResultState<Subscription>>

    fun cancelAutoRenewal(): Flow<ResultState<Subscription>>

    fun isServiceAvailable(): Flow<ResultState<Boolean>>

    fun fetchSubscriptionStatus(): Flow<ResultState<Subscription>>

    fun canCreateArea(): Flow<ResultState<Boolean>>

    suspend fun shouldSuggestTrial(): Boolean

}