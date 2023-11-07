package com.velkonost.getbetter.shared.features.likes.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserLikeRequest(
    @SerialName("entityType")
    val entityType: String,

    @SerialName("entityId")
    val entityId: Int,

    @SerialName("isPositive")
    val isPositive: Boolean?
)