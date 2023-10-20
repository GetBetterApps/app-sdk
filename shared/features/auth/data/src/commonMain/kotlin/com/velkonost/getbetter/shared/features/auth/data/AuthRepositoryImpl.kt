package com.velkonost.getbetter.shared.features.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.core.util.flowRequest2
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.auth.data.remote.AuthRemoteDataSource
import com.velkonost.getbetter.shared.features.auth.data.remote.model.request.RegisterEmailRequest
import com.velkonost.getbetter.shared.features.auth.data.remote.model.response.KtorUserInfo
import com.velkonost.getbetter.shared.features.auth.data.remote.model.response.asExternalModel
import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : AuthRepository<UserInfo> {

    override suspend fun loginEmail(
        email: String,
        password: String
    ): Flow<ResultState<UserInfo>> = flowRequest2(KtorUserInfo::asExternalModel) {
        val body = RegisterEmailRequest(email, password)
        remoteDataSource.loginEmail(body)

//        if (result.status.code != 200) {
//            return@flowRequest ResultState.Failure()
//        }

//        if (result.status.code == 200) {
//            emit(ResultState.Success(result.data!!.asExternalModel))
//        } else {
//            emit(ResultState.Failure(errorCode = result.status.code))
//        }
    }

    override suspend fun registerEmail(
        email: String,
        password: String
    ): Flow<ResultState<UserInfo>> = flowRequest {
        val body = RegisterEmailRequest(email, password)
        val result = remoteDataSource.registerEmail(body)

        result.data!!.asExternalModel()
    }

    override suspend fun registerAnonymously(): Flow<ResultState<UserInfo>> =
        flowRequest2(KtorUserInfo::asExternalModel) {
            remoteDataSource.registerAnonymously()
        }

    override fun isUserLoggedIn(): Boolean {
        return false
    }

    override suspend fun logout(): Flow<ResultState<Unit>> = flowRequest {
    }

}