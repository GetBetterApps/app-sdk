package com.velkonost.getbetter.shared.features.auth.data.remote.model.response

import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorUserInfo(
    @SerialName("name")
    val name: String? = null,

    @SerialName("email")
    val email: String? = null,
)

fun KtorUserInfo.asExternalModel() =
    UserInfo(
        registrationDate = 0L,
        lastLoginDate = 0L,
        locale = "",
        displayName = name,
        email = email
    )