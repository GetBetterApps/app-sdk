package com.velkonost.getbetter.shared.features.auth.data

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl : AuthRepository<UserInfo> {

    override suspend fun loginEmail(email: String, password: String): Flow<ResultState<UserInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun registerEmail(
        email: String,
        password: String
    ): Flow<ResultState<UserInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun registerAnonymously(): Flow<ResultState<UserInfo>> {
        TODO("Not yet implemented")
    }

    override fun isUserLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

}