package com.velkonost.getbetter.shared.features.affirmations.api

import com.velkonost.getbetter.shared.core.model.task.Affirmation
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface AffirmationsRepository {

    fun updateFavorite(
        affirmationId: Int,
        isFavorite: Boolean
    ): Flow<ResultState<List<Affirmation>>>

    fun getFavoritesList(): Flow<ResultState<List<Affirmation>>>

    fun getAffirmationForOnboarding(): Flow<ResultState<Affirmation>>
}