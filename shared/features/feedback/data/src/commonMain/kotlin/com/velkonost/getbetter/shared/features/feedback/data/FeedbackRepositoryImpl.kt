package com.velkonost.getbetter.shared.features.feedback.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.feedback.Feedback
import com.velkonost.getbetter.shared.core.model.feedback.FeedbackType
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.feedback.api.FeedbackRepository
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.FeedbackRemoteDataSource
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.request.AddAnswerFeedbackRequest
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.request.CreateFeedbackRequest
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.response.KtorFeedback
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow

class FeedbackRepositoryImpl(
    private val remoteDataSource: FeedbackRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : FeedbackRepository {

    override fun createFeedback(type: FeedbackType, text: String): Flow<ResultState<Feedback>> =
        flowRequest(
            mapper = KtorFeedback::asExternalModel,
            request = {
                val token = localDataSource.getUserToken()
                val body = CreateFeedbackRequest(type.responseName, text)
                remoteDataSource.create(token, body)
            }
        )

    override fun addAnswer(feedbackId: Int, text: String): Flow<ResultState<Feedback>> =
        flowRequest(
            mapper = KtorFeedback::asExternalModel,
            request = {
                val token = localDataSource.getUserToken()
                val body = AddAnswerFeedbackRequest(feedbackId, text)
                remoteDataSource.addAnswer(token, body)
            }
        )

    override fun getList(): Flow<ResultState<List<Feedback>>> = flowRequest(
        mapper = List<KtorFeedback>::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.fetchFeedbacks(token)
        }
    )

}