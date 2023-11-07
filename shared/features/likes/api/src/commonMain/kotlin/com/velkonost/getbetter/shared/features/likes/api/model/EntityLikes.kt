package com.velkonost.getbetter.shared.features.likes.api.model

data class EntityLikes(
    val total: Int,
    val userLikeType: LikeType
)

enum class LikeType(val responseName: String) {
    Positive("positive"),
    Negative("negative"),
    None("none")
}