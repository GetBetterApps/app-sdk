package com.velkonost.getbetter.shared.features.follows.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.follows.data.remote.model.request.UpdateFollowStateRequest
import com.velkonost.getbetter.shared.features.follows.data.remote.model.response.KtorFollows
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

class FollowsRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun addFollow(
        token: String?,
        body: UpdateFollowStateRequest
    ): RemoteResponse<KtorFollows> = httpClient.post {
        makeRequest(
            path = Route.ADD_FOLLOW,
            body = body,
            token = token
        )
    }.body()

    suspend fun removeFollow(
        token: String?,
        body: UpdateFollowStateRequest
    ): RemoteResponse<KtorFollows> = httpClient.post {
        makeRequest(
            path = Route.REMOVE_FOLLOW,
            body = body,
            token = token
        )
    }.body()

    suspend fun fetchUserFollowers(
        token: String?
    ): RemoteResponse<KtorFollows> = httpClient.get {
        makeRequest(
            path = Route.GET_USER_FOLLOWERS,
            token = token
        )
    }.body()

    suspend fun fetchUserFollows(
        token: String?
    ): RemoteResponse<KtorFollows> = httpClient.get {
        makeRequest(
            path = Route.GET_USER_FOLLOWS,
            token = token
        )
    }.body()
}