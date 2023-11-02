package com.velkonost.getbetter.shared.features.userinfo.api

import com.velkonost.getbetter.shared.core.model.user.UserInfo
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {

    suspend fun fetchInfo(): Flow<ResultState<UserInfo>>

    suspend fun initSettings(email: String): Flow<ResultState<UserInfo>>

    suspend fun updateRegistrationTime(): Flow<ResultState<UserInfo>>

    suspend fun updateLastLogin(): Flow<ResultState<UserInfo>>

    suspend fun updateLocale(locale: String): Flow<ResultState<UserInfo>>

    suspend fun updateName(newName: String): Flow<ResultState<UserInfo>>

    suspend fun updateAvatarUrl(
        fileContent: ByteArray
    ): Flow<ResultState<UserInfo>>

    suspend fun logout(): Flow<ResultState<UserInfo>>

}