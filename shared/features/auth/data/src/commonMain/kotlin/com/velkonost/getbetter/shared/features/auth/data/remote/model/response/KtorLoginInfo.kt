package com.velkonost.getbetter.shared.features.auth.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorLoginInfo(

    @SerialName("token")
    val token: String,

    @SerialName("name")
    val name: String? = null,

    @SerialName("email")
    val email: String? = null,
)

fun KtorLoginInfo.asExternalModel() = token