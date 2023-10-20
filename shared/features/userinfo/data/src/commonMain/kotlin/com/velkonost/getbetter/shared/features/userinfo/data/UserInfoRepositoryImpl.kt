package com.velkonost.getbetter.shared.features.userinfo.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.TOKEN_KEY
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.core.util.locale
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import com.velkonost.getbetter.shared.features.userinfo.data.remote.UserInfoRemoteDataSource
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.request.InitSettingsRequest
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.request.UpdateValueRequest
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.KtorUserInfo
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class UserInfoRepositoryImpl
constructor(
    private val remoteDataSource: UserInfoRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : UserInfoRepository {

    override suspend fun fetchInfo(): Flow<ResultState<UserInfo>> = flowRequest(
        mapper = KtorUserInfo::asExternalModel,
        request = {
            val token = getUserToken()
            remoteDataSource.getInfo(token)
        }
    )

    override suspend fun initSettings(email: String): Flow<ResultState<UserInfo>> = flowRequest(
        mapper = KtorUserInfo::asExternalModel,
        request = {
            val token = getUserToken()
            var name = email.substringBefore("@")
            if (name.isEmpty()) name = "Anon"

            val body = InitSettingsRequest(
                name = name,
                locale = locale
            )

            remoteDataSource.initSettings(token, body)
        }
    )

    override suspend fun updateRegistrationTime(): Flow<ResultState<UserInfo>> = flowRequest(
        mapper = KtorUserInfo::asExternalModel,
        request = {
            val token = getUserToken()
            remoteDataSource.updateRegistrationDate(token)
        }
    )

    override suspend fun updateLastLogin(): Flow<ResultState<UserInfo>> = flowRequest(
        mapper = KtorUserInfo::asExternalModel,
        request = {
            val token = getUserToken()
            remoteDataSource.updateLastLogin(token)
        }
    )

    override suspend fun updateLocale(locale: String): Flow<ResultState<UserInfo>> = flowRequest(
        mapper = KtorUserInfo::asExternalModel,
        request = {
            val token = getUserToken()
            val body = UpdateValueRequest(locale)
            remoteDataSource.updateLocale(token, body)
        }
    )

    override suspend fun updateName(newName: String): Flow<ResultState<UserInfo>> = flowRequest(
        mapper = KtorUserInfo::asExternalModel,
        request = {
            val token = getUserToken()
            val body = UpdateValueRequest(newName)
            remoteDataSource.updateName(token, body)
        }
    )

    override suspend fun updateAvatarUrl(newUrl: String): Flow<ResultState<UserInfo>> {
        TODO("Not yet implemented")
    }

    private suspend fun getUserToken(): String? =
        localDataSource.data.first()[TOKEN_KEY]

}