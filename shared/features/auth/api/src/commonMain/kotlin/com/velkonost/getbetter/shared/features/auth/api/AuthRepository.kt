package com.velkonost.getbetter.shared.features.auth.api

import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository<T> {

    suspend fun loginEmail(email: String, password: String): Flow<ResultState<T>>

    suspend fun registerEmail(email: String, password: String): Flow<ResultState<T>>

    suspend fun registerAnonymously(): Flow<ResultState<T>>

    suspend fun isUserLoggedIn(): Boolean

    suspend fun checkNeedsResetState(): Boolean
}