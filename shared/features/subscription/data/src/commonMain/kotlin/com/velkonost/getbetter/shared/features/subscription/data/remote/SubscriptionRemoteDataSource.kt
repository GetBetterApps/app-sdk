package com.velkonost.getbetter.shared.features.subscription.data.remote

import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import io.ktor.client.HttpClient

class SubscriptionRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun startTrial(
        token: String?
    ): RemoteResponse<>

}