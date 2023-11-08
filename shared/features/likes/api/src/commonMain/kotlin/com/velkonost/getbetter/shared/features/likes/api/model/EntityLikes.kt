package com.velkonost.getbetter.shared.features.likes.api.model

import com.velkonost.getbetter.shared.core.model.LikeType

data class EntityLikes(
    val total: Int,
    val userLikeType: LikeType
)

