package com.velkonost.getbetter.shared.features.userinfo.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @SerialName(registrationDatePropertyName)
    val registrationDate: Long,

    @SerialName(lastLoginDatePropertyName)
    val lastLoginDate: Long,

    @SerialName(localePropertyName)
    val locale: String,

    @SerialName(displayNamePropertyName)
    val displayName: String? = null,

    @SerialName(avatarUrlPropertyName)
    val avatar: ByteArray? = null,

    @SerialName("email")
    val email: String? = null

) {
    companion object {
        const val registrationDatePropertyName = "registrationDate"
        const val lastLoginDatePropertyName = "lastLoginDate"
        const val localePropertyName = "locale"
        const val displayNamePropertyName = "displayName"
        const val avatarUrlPropertyName = "avatarUrl"
    }
}