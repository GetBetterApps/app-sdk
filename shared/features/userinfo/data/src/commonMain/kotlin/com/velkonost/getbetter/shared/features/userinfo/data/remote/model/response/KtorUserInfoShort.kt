package com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
import com.velkonost.getbetter.shared.core.model.user.asExperienceData
import com.velkonost.getbetter.shared.core.network.AVATAR_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorUserInfoShort(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String? = null,

    @SerialName("avatar")
    val avatar: String? = null,

    @SerialName("experience")
    val experience: Int = 0,

    @SerialName("isFollows")
    val isFollows: Boolean = false,

    @SerialName("isBlocked")
    val isBlocked: Boolean = false
)

fun KtorUserInfoShort.asExternalModel() =
    UserInfoShort(
        id = id,
        displayName = name,
        avatarUrl = "$AVATAR_URL$id",
        experienceData = experience.asExperienceData,
        isFollows = isFollows,
        isBlocked = isBlocked
    )