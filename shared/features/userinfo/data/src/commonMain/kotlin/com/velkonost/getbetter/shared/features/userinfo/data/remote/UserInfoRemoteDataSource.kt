package com.velkonost.getbetter.shared.features.userinfo.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.request.ChangePasswordRequest
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.request.InitSettingsRequest
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.request.UpdateValueRequest
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.KtorUserInfo
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.KtorUserInfoShort
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.path

class UserInfoRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getInfo(token: String?): RemoteResponse<KtorUserInfo> =
        httpClient.get {
            makeRequest(
                path = Route.FETCH_INFO,
                token = token
            )
        }.body()

    suspend fun getShortInfo(token: String?, userId: String): RemoteResponse<KtorUserInfoShort> =
        httpClient.get {
            makeRequest(
                path = Route.FETCH_SHORT_INFO,
                token = token,
                params = mapOf(
                    "userId" to userId
                )
            )
        }.body()

    suspend fun initSettings(
        token: String?,
        body: InitSettingsRequest
    ): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            makeRequest(
                path = Route.INIT_SETTINGS,
                token = token,
                body = body
            )
        }.body()

    suspend fun updateName(
        token: String?,
        body: UpdateValueRequest
    ): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            makeRequest(
                path = Route.UPDATE_NAME,
                token = token,
                body = body
            )
        }.body()

    suspend fun updateLocale(
        token: String?,
        body: UpdateValueRequest
    ): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            makeRequest(
                path = Route.UPDATE_LOCALE,
                token = token,
                body = body
            )
        }.body()

    suspend fun updateLastLogin(token: String?): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            makeRequest(
                path = Route.UPDATE_LAST_LOGIN,
                token = token
            )
        }.body()

    suspend fun updateRegistrationDate(token: String?): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            makeRequest(
                path = Route.UPDATE_REGISTRATION_TIME,
                token = token
            )
        }.body()

    suspend fun performLogout(token: String?): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            makeRequest(
                path = Route.LOGOUT,
                token = token
            )
        }.body()

    suspend fun changePassword(
        token: String?,
        body: ChangePasswordRequest
    ): RemoteResponse<KtorUserInfo> = httpClient.post {
        makeRequest(
            path = Route.CHANGE_PASSWORD,
            token = token,
            body = body
        )
    }.body()

    suspend fun deleteAccount(
        token: String?
    ): RemoteResponse<Unit> = httpClient.post {
        makeRequest(
            path = Route.DELETE_ACCOUNT,
            token = token
        )
    }.body()

    suspend fun updateAvatar(
        token: String,
        fileContent: ByteArray
    ): RemoteResponse<KtorUserInfo> =
        httpClient.post {
            url {
                path(Route.UPLOAD_AVATAR)
                header("Authorization", token)

                val body = MultiPartFormDataContent(
                    formData {
                        append(
                            "file",
                            fileContent,
                            Headers.build {
                                append(HttpHeaders.ContentType, "image/jpg")
                                append(HttpHeaders.ContentDisposition, " filename=file.jpeg")
                            }
                        )
                    }
                )
                setBody(body)
            }
        }.body()

}