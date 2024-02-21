package com.velkonost.getbetter.shared.features.subscription.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.subscription.Subscription
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import com.velkonost.getbetter.shared.features.subscription.api.model.SubscriptionPayment
import com.velkonost.getbetter.shared.features.subscription.data.remote.SubscriptionRemoteDataSource
import com.velkonost.getbetter.shared.features.subscription.data.remote.model.request.CreateSubscriptionRequest
import com.velkonost.getbetter.shared.features.subscription.data.remote.model.response.KtorCreateSubscription
import com.velkonost.getbetter.shared.features.subscription.data.remote.model.response.KtorSubscription
import com.velkonost.getbetter.shared.features.subscription.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow

class SubscriptionRepositoryImpl(
    private val localDataSource: DataStore<Preferences>,
    private val remoteDataSource: SubscriptionRemoteDataSource
) : SubscriptionRepository {

    override fun createSubscription(subscriptionType: String): Flow<ResultState<SubscriptionPayment>> =
        flowRequest(
            mapper = KtorCreateSubscription::asExternalModel,
            request = {
                val token = localDataSource.getUserToken()
                val body = CreateSubscriptionRequest(subscriptionType)
                remoteDataSource.createSubscription(token, body)
            }
        )

    override fun startTrial(): Flow<ResultState<Subscription>> = flowRequest(
        mapper = KtorSubscription::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.startTrial(token)
        }
    )

    override fun cancelAutoRenewal(): Flow<ResultState<Subscription>> = flowRequest(
        mapper = KtorSubscription::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.cancelAutoRenewal(token)
        }
    )

    override fun isServiceAvailable(): Flow<ResultState<Boolean>> = flowRequest(
        mapper = { this },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.isSubscriptionsAvailable(token)
        }
    )

    override fun fetchSubscriptionStatus(): Flow<ResultState<Subscription>> = flowRequest(
        mapper = KtorSubscription::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getSubscriptionStatus(token)
        }
    )

}