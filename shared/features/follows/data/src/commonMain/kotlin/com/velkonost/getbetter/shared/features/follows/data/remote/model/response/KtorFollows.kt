package com.velkonost.getbetter.shared.features.follows.data.remote.model.response

import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.KtorUserInfoShort
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorFollows(
    @SerialName("items")
    val items: List<KtorUserInfoShort>
)