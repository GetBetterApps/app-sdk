package com.velkonost.getbetter.shared.features.userinfo.api

import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {

    suspend fun initSettings(email: String): Flow<ResultState<Unit>>

    suspend fun updateRegistrationTime(): Flow<ResultState<Unit>>

    suspend fun updateLastLogin(): Flow<ResultState<Unit>>

    suspend fun updateLocale(locale: String): Flow<ResultState<Unit>>

    suspend fun updateName(newName: String): Flow<ResultState<Unit>>

}