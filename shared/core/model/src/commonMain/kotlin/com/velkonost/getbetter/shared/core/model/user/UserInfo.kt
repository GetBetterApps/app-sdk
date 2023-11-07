package com.velkonost.getbetter.shared.core.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val registrationDate: Long,

    val lastLoginDate: Long,

    val locale: String,

    val displayName: String? = null,

    val avatar: ByteArray? = null,

    val email: String? = null,

    val experience: Int = 0

)

@Serializable
data class UserInfoShort(
    val id: String,

    val displayName: String? = null,

    val avatar: ByteArray? = null,

    val experience: Int = 0
)