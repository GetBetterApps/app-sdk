package com.velkonost.getbetter.shared.features.affirmations.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.task.Affirmation
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.affirmations.api.AffirmationsRepository
import com.velkonost.getbetter.shared.features.affirmations.data.remote.AffirmationsRemoteDataSource
import com.velkonost.getbetter.shared.features.affirmations.data.remote.model.request.UpdateAffirmationFavoriteRequest
import com.velkonost.getbetter.shared.features.affirmations.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow

class AffirmationsRepositoryImpl
constructor(
    private val remoteDataSource: AffirmationsRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : AffirmationsRepository {

    override fun updateFavorite(
        affirmationId: Int,
        isFavorite: Boolean
    ): Flow<ResultState<List<Affirmation>>> = flowRequest(
        mapper = { map { it.asExternalModel() } },
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateAffirmationFavoriteRequest(
                affirmationId = affirmationId,
                isFavorite = isFavorite
            )
            remoteDataSource.updateFavorite(token, body)
        }
    )

    override fun getFavoritesList(): Flow<ResultState<List<Affirmation>>> = flowRequest(
        mapper = { map { it.asExternalModel() } },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getFavoritesList(token)
        }
    )

}