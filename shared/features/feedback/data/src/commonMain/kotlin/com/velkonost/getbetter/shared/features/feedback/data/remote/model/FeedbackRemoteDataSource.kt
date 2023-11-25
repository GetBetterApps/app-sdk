package com.velkonost.getbetter.shared.features.feedback.data.remote.model

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import com.velkonost.getbetter.shared.features.feedback.data.remote.Route
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.request.AddAnswerFeedbackRequest
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.request.CreateFeedbackRequest
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.response.KtorFeedback
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post

class FeedbackRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun create(
        token: String?,
        body: CreateFeedbackRequest
    ): RemoteResponse<KtorFeedback> = httpClient.post {
        makeRequest(
            path = Route.CREATE,
            body = body,
            token = token
        )
    }.body()

    suspend fun addAnswer(
        token: String?,
        body: AddAnswerFeedbackRequest
    ): RemoteResponse<KtorFeedback> = httpClient.post {
        makeRequest(
            path = Route.ADD_ANSWER,
            body = body,
            token = token
        )
    }.body()

}