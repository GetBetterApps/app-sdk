package com.velkonost.getbetter.shared.features.affirmations.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.affirmations.data.remote.model.request.UpdateAffirmationFavoriteRequest
import com.velkonost.getbetter.shared.features.affirmations.data.remote.model.response.KtorAffirmation
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

class AffirmationsRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun updateFavorite(
        token: String?,
        body: UpdateAffirmationFavoriteRequest
    ): RemoteResponse<List<KtorAffirmation>> = httpClient.post {
        makeRequest(
            path = Route.UPDATE_FAVORITE,
            token = token,
            body = body
        )
    }.body()

    suspend fun getFavoritesList(
        token: String?
    ): RemoteResponse<List<KtorAffirmation>> = httpClient.get {
        makeRequest(
            path = Route.FAVORITES_LIST,
            token = token,
            body = body
        )
    }.body()
}