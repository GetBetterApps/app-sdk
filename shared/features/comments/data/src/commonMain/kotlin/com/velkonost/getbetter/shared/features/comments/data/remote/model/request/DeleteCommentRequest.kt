package com.velkonost.getbetter.shared.features.comments.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteCommentRequest(
    @SerialName("commentId")
    val commentId: Int
)