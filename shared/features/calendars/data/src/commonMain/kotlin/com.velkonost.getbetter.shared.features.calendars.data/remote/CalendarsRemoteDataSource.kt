package com.velkonost.getbetter.shared.features.calendars.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.calendars.data.remote.model.response.KtorUserAction
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CalendarsRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchDateItems(
        token: String?,
        startOfDay: Long
    ): RemoteResponse<List<KtorUserAction>> = httpClient.get {
        makeRequest(
            path = Route.FETCH_DATE_ITEMS,
            token = token,
            params = mapOf(
                "dayStart" to startOfDay
            )
        )
    }.body()

}