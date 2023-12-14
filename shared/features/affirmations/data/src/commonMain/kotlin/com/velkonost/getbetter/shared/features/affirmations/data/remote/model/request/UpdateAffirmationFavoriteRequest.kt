package com.velkonost.getbetter.shared.features.affirmations.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAffirmationFavoriteRequest(
    val affirmationId: Int,
    val isFavorite: Boolean
)