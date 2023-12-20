package com.velkonost.getbetter.shared.features.abilities.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.HINT_ABILITIES_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.abilities.data.remote.AbilitiesRemoteDataSource
import com.velkonost.getbetter.shared.features.abilities.data.remote.model.response.KtorAbilityDetail
import com.velkonost.getbetter.shared.features.abilities.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class AbilitiesRepositoryImpl(
    private val remoteDataSource: AbilitiesRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : AbilitiesRepository {

    override fun getAll(page: Int, pageSize: Int): Flow<ResultState<List<Ability>>> = flowRequest(
        mapper = { map { it.asExternalModel() } },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getAll(token, page, pageSize)
        }
    )

    override fun getDetails(abilityId: Int): Flow<ResultState<Ability>> = flowRequest(
        mapper = KtorAbilityDetail::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getDetails(token, abilityId)
        }
    )

    override fun getAbilitiesForOnboarding(): Flow<ResultState<List<Ability>>> = flowRequest(
        mapper = { map { it.asExternalModel() } },
        request = { remoteDataSource.getDemo() }
    )

    override suspend fun shouldShowHint(): Boolean {
        val value = localDataSource.data.first()[HINT_ABILITIES_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_ABILITIES_SHOULD_SHOW] = false
        }

        return value
    }

}