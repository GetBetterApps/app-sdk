package com.velkonost.getbetter.shared.features.abilities.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.abilities.data.remote.model.response.KtorAbility
import com.velkonost.getbetter.shared.features.abilities.data.remote.model.response.KtorAbilityDetail
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AbilitiesRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getAll(
        token: String?,
        page: Int,
        pageSize: Int
    ): RemoteResponse<List<KtorAbility>> = httpClient.get {
        makeRequest(
            path = Route.GET_ALL,
            token = token,
            params = hashMapOf(
                "page" to page,
                "pageSize" to pageSize
            )
        )
    }.body()

    suspend fun getDetails(
        token: String?,
        abilityId: Int
    ): RemoteResponse<KtorAbilityDetail> = httpClient.get {
        makeRequest(
            path = Route.GET_DETAILS,
            token = token,
            params = hashMapOf(
                "abilityId" to abilityId
            )
        )
    }.body()

    suspend fun getDemo(): RemoteResponse<List<KtorAbility>> = httpClient.get {
        makeRequest(path = Route.GET_DEMO)
    }.body()

}