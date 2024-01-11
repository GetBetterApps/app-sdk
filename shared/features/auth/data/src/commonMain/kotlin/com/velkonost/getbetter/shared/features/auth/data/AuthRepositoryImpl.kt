package com.velkonost.getbetter.shared.features.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.NEW_USER_RESET_AUTH_STATE
import com.velkonost.getbetter.shared.core.datastore.TOKEN_KEY
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.network.PRIVACY_URL
import com.velkonost.getbetter.shared.core.network.TERMS_URL
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

    override suspend fun identifyAnonymous(
        email: String,
        password: String
    ): Flow<ResultState<String>> = flowRequest(
        mapper = KtorLoginInfo::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = RegisterEmailRequest(email, password)
            remoteDataSource.identifyAnonymous(token, body)
        }
    )

    override suspend fun isUserLoggedIn(): Boolean =
        localDataSource.data.first().contains(TOKEN_KEY)
                && localDataSource.data.first()[TOKEN_KEY]!!.isNotEmpty()

    override suspend fun checkNeedsResetState(): Boolean {
        val value = localDataSource.data.first()[NEW_USER_RESET_AUTH_STATE] == true
        localDataSource.edit { preferences ->
            preferences[NEW_USER_RESET_AUTH_STATE] = false
        }

        return value
    }

    override fun getPrivacyLink(): String = PRIVACY_URL

    override fun getTermsLink(): String = TERMS_URL

    private suspend fun saveAuthToken(value: String) {
        localDataSource.edit { preferences ->
            preferences[TOKEN_KEY] = value
        }
    }
}