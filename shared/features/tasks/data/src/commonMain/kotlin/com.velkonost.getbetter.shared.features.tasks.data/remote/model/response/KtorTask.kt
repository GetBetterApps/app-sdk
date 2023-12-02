package com.velkonost.getbetter.shared.features.tasks.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorTask(
    @SerialName("id")
    val id: Int?,

    @SerialName("area")
    var area: AreaResponse?,

    @SerialName("name")
    val name: String?,

    @SerialName("whatToDo")
    val whatToDo: String?,

    @SerialName("why")
    val why: String?,

    @SerialName("recommendedTime")
    val recommendedTime: Int?,

    @SerialName("requiredLevel")
    val requiredLevel: Int?,

    @SerialName("abilities")
    var abilities: List<AbilityResponse>,

    @SerialName("isFavorite")
    var isFavorite: Boolean,

    @SerialName("isNotInteresting")
    var isNotInteresting: Boolean,

    @SerialName("isCompleted")
    var isCompleted: Boolean
)