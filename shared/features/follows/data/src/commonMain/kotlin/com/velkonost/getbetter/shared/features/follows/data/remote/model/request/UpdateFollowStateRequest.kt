package com.velkonost.getbetter.shared.features.follows.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateFollowStateRequest(
    @SerialName("followUserId")
    val followUserId: String
)