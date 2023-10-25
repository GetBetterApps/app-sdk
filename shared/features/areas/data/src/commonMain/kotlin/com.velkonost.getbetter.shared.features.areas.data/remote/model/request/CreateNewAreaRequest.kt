package com.velkonost.getbetter.shared.features.areas.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNewAreaRequest(
    @SerialName("areaId")
    val areaId: Int = -1,

    @SerialName("areaName")
    val areaName: String,

    @SerialName("areaDescription")
    val areaDescription: String,

    @SerialName("isAreaPrivate")
    val isAreaPrivate: Boolean? = null,

    @SerialName("areaRequiredLevel")
    val areaRequiredLevel: Int? = null,

    @SerialName("areaEmojiId")
    val areaEmojiId: Int?,

    @SerialName("areaImageUrl")
    val areaImageUrl: String?
)