package com.velkonost.getbetter.shared.features.tasks.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.tasks.data.remote.model.request.UpdateTaskStateRequest
import com.velkonost.getbetter.shared.features.tasks.data.remote.model.response.KtorTask
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

class TasksRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchCompletedTasks(
        token: String?
    ): RemoteResponse<List<KtorTask>> = httpClient.get {
        makeRequest(
            path = Route.COMPLETED_LIST,
            token = token
        )
    }.body()

    suspend fun fetchCurrentList(
        token: String?,
        forceUpdate: Boolean = false
    ): RemoteResponse<List<KtorTask>> = httpClient.get {
        makeRequest(
            path = Route.GET_CURRENT_LIST,
            token = token,
            params = hashMapOf(
                "forceUpdate" to forceUpdate
            )
        )
    }.body()

    suspend fun addToFavorite(
        token: String?,
        body: UpdateTaskStateRequest
    ): RemoteResponse<KtorTask> = httpClient.post {
        makeRequest(
            path = Route.ADD_TO_FAVORITE,
            token = token,
            body = body
        )
    }.body()

    suspend fun remoteFromFavorite(
        token: String?,
        body: UpdateTaskStateRequest
    ): RemoteResponse<KtorTask> = httpClient.post {
        makeRequest(
            path = Route.REMOVE_FROM_FAVORITE,
            token = token,
            body = body
        )
    }.body()

    suspend fun addToNotInteresting(
        token: String?,
        body: UpdateTaskStateRequest
    ): RemoteResponse<KtorTask> = httpClient.post {
        makeRequest(
            path = Route.ADD_TO_NOT_INTERESTING,
            token = token,
            body = body
        )
    }.body()

    suspend fun removeFromNotInteresting(
        token: String?,
        body: UpdateTaskStateRequest
    ): RemoteResponse<KtorTask> = httpClient.post {
        makeRequest(
            path = Route.REMOVE_FROM_NOT_INTERESTING,
            token = token,
            body = body
        )
    }.body()

    suspend fun addToCompleted(
        token: String?,
        body: UpdateTaskStateRequest
    ): RemoteResponse<KtorTask> = httpClient.post {
        makeRequest(
            path = Route.ADD_TO_COMPLETED,
            token = token,
            body = body
        )
    }.body()

    suspend fun removeFromCompleted(
        token: String?,
        body: UpdateTaskStateRequest
    ): RemoteResponse<KtorTask> = httpClient.post {
        makeRequest(
            path = Route.REMOTE_FROM_COMPLETED,
            token = token,
            body = body
        )
    }.body()
}