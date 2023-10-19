package com.velkonost.getbetter.shared.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResponse<out T>(
    @SerialName("status")
    val status: ResponseStatus,

    @SerialName("data")
    val data: T?
)

@Serializable
data class ResponseStatus(
    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String? = null
)