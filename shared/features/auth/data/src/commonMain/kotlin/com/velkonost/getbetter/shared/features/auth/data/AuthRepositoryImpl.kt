package com.velkonost.getbetter.shared.features.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.TOKEN_KEY
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.auth.data.remote.AuthRemoteDataSource
import com.velkonost.getbetter.shared.features.auth.data.remote.model.request.RegisterEmailRequest
import com.velkonost.getbetter.shared.features.auth.data.remote.model.response.KtorLoginInfo
import com.velkonost.getbetter.shared.features.auth.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : AuthRepository<String> {

    override suspend fun loginEmail(
        email: String,
        password: String
    ): Flow<ResultState<String>> = flowRequest(
        mapper = KtorLoginInfo::asExternalModel,
        request = {
            val body = RegisterEmailRequest(email, password)
            remoteDataSource.loginEmail(body)
        },
        onSuccess = ::saveAuthToken
    )

    override suspend fun registerEmail(
        email: String,
        password: String
    ): Flow<ResultState<String>> = flowRequest(
        mapper = KtorLoginInfo::asExternalModel,
        request = {
            val body = RegisterEmailRequest(email, password)
            remoteDataSource.registerEmail(body)
        },
        onSuccess = ::saveAuthToken
    )

    override suspend fun registerAnonymously(): Flow<ResultState<String>> = flowRequest(
        mapper = KtorLoginInfo::asExternalModel,
        request = remoteDataSource::registerAnonymously,
        onSuccess = ::saveAuthToken
    )

    override suspend fun isUserLoggedIn(): Boolean =
        localDataSource.data.first().contains(TOKEN_KEY)

    private suspend fun saveAuthToken(value: String) {
        localDataSource.edit { preferences ->
            preferences[TOKEN_KEY] = value
        }
    }

}