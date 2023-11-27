package com.velkonost.getbetter.shared.features.userinfo.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordRequest(
    @SerialName("oldPassword")
    val oldPassword: String,

    @SerialName("newPassword")
    val newPassword: String,

    @SerialName("newRepeatedPassword")
    val newRepeatedPassword: String
)