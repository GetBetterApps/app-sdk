package com.velkonost.getbetter.shared.features.follows.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.follows.api.FollowsRepository
import com.velkonost.getbetter.shared.features.follows.api.model.FollowsData
import com.velkonost.getbetter.shared.features.follows.data.remote.FollowsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class FollowsRepositoryImpl
constructor(
    private val remoteDataSource: FollowsRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : FollowsRepository {

    override suspend fun addFollow(followUserId: String): Flow<ResultState<FollowsData>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFollow(followUserId: String): Flow<ResultState<FollowsData>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserFollowers(): Flow<ResultState<FollowsData>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserFollows(): Flow<ResultState<FollowsData>> {
        TODO("Not yet implemented")
    }

}