package com.velkonost.getbetter.shared.features.social.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.notes.data.remote.model.response.KtorNote
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SocialRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getAreasFeed(
        token: String?,
        page: Int,
        pageSize: Int
    ): RemoteResponse<List<KtorNote>> = httpClient.get {
        makeRequest(
            path = Route.AREAS_FEED,
            token = token,
            params = mapOf(
                "page" to page,
                "pageSize" to pageSize
            )
        )
    }.body()

    suspend fun getGeneralFeed(
        token: String?,
        page: Int,
        pageSize: Int
    ): RemoteResponse<List<KtorNote>> = httpClient.get {
        makeRequest(
            path = Route.GENERAL_FEED,
            token = token,
            params = mapOf(
                "page" to page,
                "pageSize" to pageSize
            )
        )
    }.body()
}