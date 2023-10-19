package com.velkonost.getbetter.shared.features.auth.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterEmailRequest(
    @SerialName("email")
    val email: String,

    @SerialName("password")
    val password: String
)