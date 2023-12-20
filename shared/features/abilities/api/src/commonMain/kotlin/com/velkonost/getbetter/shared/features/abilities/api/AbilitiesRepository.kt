package com.velkonost.getbetter.shared.features.abilities.api

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface AbilitiesRepository {

    fun getAll(
        page: Int,
        pageSize: Int
    ): Flow<ResultState<List<Ability>>>

    fun getDetails(
        abilityId: Int
    ): Flow<ResultState<Ability>>

    fun getAbilitiesForOnboarding(): Flow<ResultState<List<Ability>>>

    suspend fun shouldShowHint(): Boolean
}