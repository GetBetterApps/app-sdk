package com.velkonost.getbetter.shared.core.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val registrationDate: Long,

    val lastLoginDate: Long,

    val locale: String,

    val displayName: String? = null,

    val email: String? = null,

    val experienceData: ExperienceData? = null,

    val avatarUrl: String? = null

)

@Serializable
data class UserInfoShort(
    val id: String,

    val displayName: String? = null,

    val experienceData: ExperienceData? = null,

    val isFollows: Boolean = false,

    val avatarUrl: String? = null
)