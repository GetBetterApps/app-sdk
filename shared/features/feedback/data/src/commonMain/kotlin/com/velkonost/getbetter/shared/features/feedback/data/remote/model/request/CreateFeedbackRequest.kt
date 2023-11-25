package com.velkonost.getbetter.shared.features.feedback.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateFeedbackRequest(
    @SerialName("type")
    val type: String,

    @SerialName("text")
    val text: String,
)