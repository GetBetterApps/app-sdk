package com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
import com.velkonost.getbetter.shared.core.model.user.asExperienceData
import io.ktor.util.decodeBase64Bytes
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
    val isFollows: Boolean = false
)

fun KtorUserInfoShort.asExternalModel() =
    UserInfoShort(
        id = id,
        displayName = name,
        avatar = avatar?.decodeBase64Bytes(),
        experienceData = experience.asExperienceData,
        isFollows = isFollows
    )