package com.velkonost.getbetter.shared.features.comments.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentRequest(
    @SerialName("entityType")
    val entityType: String,

    @SerialName("entityId")
    val entityId: Int,

    @SerialName("text")
    val text: String
)