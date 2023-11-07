package com.velkonost.getbetter.shared.features.likes.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.likes.data.remote.model.request.UpdateUserLikeRequest
import com.velkonost.getbetter.shared.features.likes.data.remote.model.response.KtorEntityLikes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post

class LikesRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun addLike(
        token: String?,
        body: UpdateUserLikeRequest
    ): RemoteResponse<KtorEntityLikes> = httpClient.post {
        makeRequest(
            path = Route.ADD_LIKE,
            body = body,
            token = token
        )
    }.body()

}