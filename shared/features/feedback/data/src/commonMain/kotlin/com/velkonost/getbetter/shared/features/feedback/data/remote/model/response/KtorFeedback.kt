package com.velkonost.getbetter.shared.features.feedback.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorFeedback(
    @SerialName("id")
    val id: Int?,

    @SerialName("type")
    val type: String?,

    @SerialName("datetime")
    val datetime: Long?,

    @SerialName("status")
    val status: String?,

    @SerialName("messages")
    val messages: List<KtorFeedbackMessage>
)

@Serializable
data class KtorFeedbackMessage(
    @SerialName("fromUser")
    val fromUser: Boolean,

    @SerialName("text")
    val text: String?,

    @SerialName("datetime")
    val datetime: Long?
)