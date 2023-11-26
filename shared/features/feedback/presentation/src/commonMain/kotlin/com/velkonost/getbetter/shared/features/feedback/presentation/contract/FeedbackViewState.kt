package com.velkonost.getbetter.shared.features.feedback.presentation.contract

import com.velkonost.getbetter.shared.core.model.feedback.Feedback
import com.velkonost.getbetter.shared.core.model.feedback.FeedbackType
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class FeedbackViewState(
    val isLoading: Boolean = false,
    val items: List<Feedback> = emptyList(),
    val newFeedback: NewFeedbackState = NewFeedbackState(),
    val feedbackDetailsState: FeedbackDetailsState = FeedbackDetailsState()
) : UIContract.State

data class NewFeedbackState(
    val isLoading: Boolean = false,
    val text: String = "",
    val type: FeedbackType = FeedbackType.Feature
)

data class FeedbackDetailsState(
    val answerText: String = "",
)