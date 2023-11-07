package com.velkonost.getbetter.shared.features.likes.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.likes.api.LikesRepository
import com.velkonost.getbetter.shared.features.likes.api.model.EntityLikes
import com.velkonost.getbetter.shared.features.likes.api.model.LikeType
import com.velkonost.getbetter.shared.features.likes.data.remote.LikesRemoteDataSource
import com.velkonost.getbetter.shared.features.likes.data.remote.model.request.UpdateUserLikeRequest
import com.velkonost.getbetter.shared.features.likes.data.remote.model.response.KtorEntityLikes
import com.velkonost.getbetter.shared.features.likes.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow

class LikesRepositoryImpl
constructor(
    private val remoteDataSource: LikesRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : LikesRepository {

    override fun addLike(
        entityType: EntityType,
        entityId: Int,
        likeType: LikeType
    ): Flow<ResultState<EntityLikes>> = flowRequest(
        mapper = KtorEntityLikes::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateUserLikeRequest(
                entityType = entityType.responseName,
                entityId = entityId,
                isPositive = when (likeType) {
                    LikeType.Positive -> true
                    LikeType.Negative -> false
                    else -> null
                }
            )

            remoteDataSource.addLike(token, body)
        }
    )


}