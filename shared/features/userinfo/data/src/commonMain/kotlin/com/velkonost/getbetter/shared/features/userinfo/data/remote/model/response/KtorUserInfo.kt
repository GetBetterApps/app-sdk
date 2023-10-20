package com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorUserInfo(
    @SerialName("name")
    val name: String? = null,

    @SerialName("email")
    val email: String? = null
)