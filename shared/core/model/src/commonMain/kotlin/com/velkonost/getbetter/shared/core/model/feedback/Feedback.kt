package com.velkonost.getbetter.shared.core.model.feedback

data class Feedback(
    val id: Int?,
    val type: FeedbackType,
    val status: FeedbackStatus,
    val datetime: Long?,
    val message: List<FeedbackMessage>

)

data class FeedbackMessage(
    val fromUser: Boolean, val text: String, val datetime: Long
)