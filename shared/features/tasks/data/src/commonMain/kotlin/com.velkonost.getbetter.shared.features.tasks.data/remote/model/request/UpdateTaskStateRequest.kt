package com.velkonost.getbetter.shared.features.tasks.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTaskStateRequest(
    @SerialName("taskId")
    val taskId: Int
)