package com.velkonost.getbetter.shared.core.model.likes

import com.velkonost.getbetter.shared.core.util.randomUUID
import kotlinx.serialization.Serializable

@Serializable
data class LikesData(
    val id: String = randomUUID(),

    var isLikesLoading: Boolean = false,

    var totalLikes: Int,

    var userLike: LikeType
)