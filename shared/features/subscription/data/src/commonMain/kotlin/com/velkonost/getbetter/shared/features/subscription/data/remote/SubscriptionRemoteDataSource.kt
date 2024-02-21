package com.velkonost.getbetter.shared.features.subscription.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.subscription.data.remote.model.request.CreateSubscriptionRequest
import com.velkonost.getbetter.shared.features.subscription.data.remote.model.response.KtorCreateSubscription
import com.velkonost.getbetter.shared.features.subscription.data.remote.model.response.KtorSubscription
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

class SubscriptionRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun startTrial(
        token: String?
    ): RemoteResponse<KtorSubscription> = httpClient.post {
        makeRequest(
            path = Route.START_TRIAL,
            token = token
        )
    }.body()

    suspend fun createSubscription(
        token: String?,
        body: CreateSubscriptionRequest
    ): RemoteResponse<KtorCreateSubscription> = httpClient.post {
        makeRequest(
            path = Route.CREATE,
            token = token,
            body = body
        )
    }.body()

    suspend fun cancelAutoRenewal(
        token: String?
    ): RemoteResponse<KtorSubscription> = httpClient.post {
        makeRequest(
            path = Route.CANCEL,
            token = token
        )
    }.body()

    suspend fun isSubscriptionsAvailable(
        token: String?
    ): RemoteResponse<Boolean> = httpClient.get {
        makeRequest(
            path = Route.AVAILABLE,
            token = token
        )
    }.body()

    suspend fun getSubscriptionStatus(
        token: String?
    ): RemoteResponse<KtorSubscription> = httpClient.get {
        makeRequest(
            path = Route.STATUS,
            token = token
        )
    }.body()

}