package com.velkonost.getbetter.shared.features.auth.data.remote

import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.auth.data.remote.model.request.RegisterEmailRequest
import com.velkonost.getbetter.shared.features.auth.data.remote.model.response.KtorUserInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path

class AuthRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun registerEmail(body: RegisterEmailRequest): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            url {
                path("auth/registerEmail")
                setBody(body)
            }
        }.body()

    suspend fun loginEmail(body: RegisterEmailRequest): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            url {
                path("auth/loginEmail")
                setBody(body)
            }
        }.body()

    suspend fun registerAnonymously(): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            url {
                path("auth/registerAnonymously")
            }
        }.body()

}