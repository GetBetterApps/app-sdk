package com.velkonost.getbetter.shared.features.affirmations.data.remote.model.request

data class UpdateAffirmationFavoriteRequest(
    val affirmationId: Int,
    val isFavorite: Boolean
)