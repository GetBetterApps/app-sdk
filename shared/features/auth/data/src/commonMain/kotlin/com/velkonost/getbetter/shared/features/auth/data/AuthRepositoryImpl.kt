package com.velkonost.getbetter.shared.features.auth.data

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.auth.data.remote.AuthRemoteDataSource
import com.velkonost.getbetter.shared.features.auth.data.remote.model.request.RegisterEmailRequest
import com.velkonost.getbetter.shared.features.auth.data.remote.model.response.asExternalModel
import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository<UserInfo> {

    override suspend fun loginEmail(
        email: String,
        password: String
    ): Flow<ResultState<UserInfo>> = flowRequest {
        val body = RegisterEmailRequest(email, password)
        val result = remoteDataSource.loginEmail(body)

        result.data!!.asExternalModel
    }

    override suspend fun registerEmail(
        email: String,
        password: String
    ): Flow<ResultState<UserInfo>> = flowRequest {
        val body = RegisterEmailRequest(email, password)
        val result = remoteDataSource.registerEmail(body)

        result.data!!.asExternalModel
    }

    override suspend fun registerAnonymously(): Flow<ResultState<UserInfo>> = flowRequest {
        val result = remoteDataSource.registerAnonymously()

        result.data!!.asExternalModel
    }

    override fun isUserLoggedIn(): Boolean {
        return false
    }

    override suspend fun logout(): Flow<ResultState<Unit>> = flowRequest {
    }

}