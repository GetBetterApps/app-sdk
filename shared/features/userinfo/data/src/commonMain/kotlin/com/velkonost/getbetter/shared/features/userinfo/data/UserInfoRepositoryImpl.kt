package com.velkonost.getbetter.shared.features.userinfo.data

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import com.velkonost.getbetter.shared.features.userinfo.data.remote.UserInfoRemoteDataSource
import kotlinx.coroutines.flow.Flow

class UserInfoRepositoryImpl
constructor(private val remoteDataSource: UserInfoRemoteDataSource) : UserInfoRepository {
    override suspend fun fetchInfo(): Flow<ResultState<UserInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun initSettings(email: String): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateRegistrationTime(): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLastLogin(): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocale(locale: String): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateName(newName: String): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateAvatarUrl(newUrl: String): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

}