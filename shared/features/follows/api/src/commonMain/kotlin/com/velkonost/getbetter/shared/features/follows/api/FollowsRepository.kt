package com.velkonost.getbetter.shared.features.follows.api

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.follows.api.model.FollowsData
import kotlinx.coroutines.flow.Flow

interface FollowsRepository {

    suspend fun addFollow(
        followUserId: String
    ): Flow<ResultState<FollowsData>>

    suspend fun removeFollow(
        followUserId: String
    ): Flow<ResultState<FollowsData>>

    suspend fun getUserFollowers(): Flow<ResultState<FollowsData>>

    suspend fun getUserFollows(): Flow<ResultState<FollowsData>>

}