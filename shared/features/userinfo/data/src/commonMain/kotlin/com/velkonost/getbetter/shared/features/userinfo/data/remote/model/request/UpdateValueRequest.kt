package com.velkonost.getbetter.shared.features.userinfo.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateValueRequest(
    @SerialName("new_value")
    val value: String
)