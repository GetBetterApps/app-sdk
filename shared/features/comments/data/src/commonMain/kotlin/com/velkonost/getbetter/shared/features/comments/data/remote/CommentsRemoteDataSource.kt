package com.velkonost.getbetter.shared.features.comments.data.remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.comments.data.remote.model.request.CreateCommentRequest
import com.velkonost.getbetter.shared.features.comments.data.remote.model.request.DeleteCommentRequest
import com.velkonost.getbetter.shared.features.comments.data.remote.model.response.KtorComment
import com.velkonost.getbetter.shared.features.comments.data.remote.model.response.KtorCommentsList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

class CommentsRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun createComment(
        token: String?,
        body: CreateCommentRequest
    ): RemoteResponse<KtorCommentsList> = httpClient.post {
        makeRequest(
            path = Route.CREATE_COMMENT,
            body = body,
            token = token
        )
    }.body()

    suspend fun deleteComment(
        token: String?,
        body: DeleteCommentRequest
    ): RemoteResponse<KtorCommentsList> = httpClient.post {
        makeRequest(
            path = Route.DELETE_COMMENT,
            body = body,
            token = token
        )
    }.body()

    suspend fun getComments(
        token: String?,
        entityType: String,
        entityId: Int
    ): RemoteResponse<KtorCommentsList> = httpClient.get {
        makeRequest(
            path = Route.FETCH_COMMENTS,
            token = token,
            params = mapOf(
                "entityType" to entityType,
                "entityId" to entityId
            )
        )
    }.body()

    suspend fun getComment(
        token: String?,
        commentId: Int
    ): RemoteResponse<KtorComment> = httpClient.get {
        makeRequest(
            path = Route.FETCH_COMMENT_DETAILS,
            token = token,
            params = mapOf(
                "commentId" to commentId
            )
        )
    }.body()

}