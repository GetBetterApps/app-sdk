package com.velkonost.getbetter.shared.features.auth.data.remote

import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.auth.data.remote.model.request.RegisterEmailRequest
import com.velkonost.getbetter.shared.features.auth.data.remote.model.response.KtorLoginInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path

class AuthRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun registerEmail(body: RegisterEmailRequest): RemoteResponse<KtorLoginInfo> =
        httpClient.post {
            url {
                path(Route.REGISTER_EMAIL)
                setBody(body)
            }
        }.body()

    suspend fun loginEmail(body: RegisterEmailRequest): RemoteResponse<KtorLoginInfo> =
        httpClient.post {
            url {
                path(Route.LOGIN_EMAIL)
                setBody(body)
            }
        }.body()

    suspend fun registerAnonymously(): RemoteResponse<KtorLoginInfo> =
        httpClient.post {
            url {
                path(Route.REGISTER_ANONYMOUSLY)
            }
        }.body()

}