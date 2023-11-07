package com.velkonost.getbetter.shared.features.likes.data.remote.model.response

import com.velkonost.getbetter.shared.features.likes.api.model.EntityLikes
import com.velkonost.getbetter.shared.features.likes.api.model.LikeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorEntityLikes(
    @SerialName("total")
    val total: Int,

    @SerialName("userLikeType")
    val userLikeType: String
)

fun KtorEntityLikes.asExternalModel() =
    EntityLikes(
        total = total,
        userLikeType = LikeType.entries.first { it.responseName == userLikeType }
    )