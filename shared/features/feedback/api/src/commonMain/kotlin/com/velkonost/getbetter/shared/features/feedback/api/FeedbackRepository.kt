package com.velkonost.getbetter.shared.features.feedback.api

import com.velkonost.getbetter.shared.core.model.feedback.Feedback
import com.velkonost.getbetter.shared.core.model.feedback.FeedbackType
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface FeedbackRepository {

    fun createFeedback(
        type: FeedbackType, text: String
    ): Flow<ResultState<Feedback>>

    fun addAnswer(
        feedbackId: Int, text: String
    ): Flow<ResultState<Feedback>>

    fun getList(): Flow<ResultState<List<Feedback>>>

}