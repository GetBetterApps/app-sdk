package com.velkonost.getbetter.shared.features.follows.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.follows.api.FollowsRepository
import com.velkonost.getbetter.shared.features.follows.api.model.FollowsData
import com.velkonost.getbetter.shared.features.follows.data.remote.FollowsRemoteDataSource
import com.velkonost.getbetter.shared.features.follows.data.remote.model.request.UpdateFollowStateRequest
import com.velkonost.getbetter.shared.features.follows.data.remote.model.response.UserType
import com.velkonost.getbetter.shared.features.follows.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow

class FollowsRepositoryImpl
constructor(
    private val remoteDataSource: FollowsRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : FollowsRepository {

    override suspend fun addFollow(followUserId: String): Flow<ResultState<FollowsData>> =
        flowRequest(
            mapper = { this asExternalModel UserType.Followers },
            request = {
                val token = localDataSource.getUserToken()
                val body = UpdateFollowStateRequest(followUserId)
                remoteDataSource.addFollow(token, body)
            }
        )

    override suspend fun removeFollow(followUserId: String): Flow<ResultState<FollowsData>> =
        flowRequest(
            mapper = { this asExternalModel UserType.Followers },
            request = {
                val token = localDataSource.getUserToken()
                val body = UpdateFollowStateRequest(followUserId)
                remoteDataSource.removeFollow(token, body)
            }
        )

    override suspend fun getUserFollowers(): Flow<ResultState<FollowsData>> = flowRequest(
        mapper = { this asExternalModel UserType.Followers },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.fetchUserFollowers(token)
        }
    )

    override suspend fun getUserFollows(): Flow<ResultState<FollowsData>> = flowRequest(
        mapper = { this asExternalModel UserType.Follows },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.fetchUserFollows(token)
        }
    )

}