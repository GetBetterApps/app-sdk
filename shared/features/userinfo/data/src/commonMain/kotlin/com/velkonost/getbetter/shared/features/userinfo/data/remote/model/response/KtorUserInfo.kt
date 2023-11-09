package com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.user.UserInfo
import com.velkonost.getbetter.shared.core.model.user.asExperienceData
import io.ktor.util.decodeBase64Bytes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorUserInfo(
    @SerialName("name")
    val name: String? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("lastLoginDate")
    val lastLoginDate: Long = 0L,

    @SerialName("registrationDate")
    val registrationDate: Long = 0L,

    @SerialName("locale")
    val locale: String = "en",

    @SerialName("avatar")
    val avatar: String? = null,

    @SerialName("experience")
    val experience: Int = 0
)

fun KtorUserInfo.asExternalModel() =
    UserInfo(
        displayName = name,
        email = email,
        lastLoginDate = lastLoginDate,
        registrationDate = registrationDate,
        locale = locale,
        avatar = avatar?.decodeBase64Bytes(),
        experienceData = experience.asExperienceData
    )