package com.velkonost.getbetter.shared.features.userinfo.data

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.core.util.locale
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserInfoRepositoryImpl
constructor(private val db: FirebaseFirestore) : UserInfoRepository {

    override suspend fun fetchInfo(): Flow<ResultState<UserInfo>> {
        val user = Firebase.auth.currentUser
        val userId = user?.uid

        return if (userId != null) {
            db.collection("users")
                .document(userId)
                .snapshots
                .map {
                    val info = UserInfo(
                        locale = it.get(UserInfo.localePropertyName),
                        avatarUrl = it.get(UserInfo.avatarUrlPropertyName),
                        displayName = it.get(UserInfo.displayNamePropertyName),
                        lastLoginDate = it.get<Timestamp>(UserInfo.lastLoginDatePropertyName).seconds,
                        registrationDate = it.get<Timestamp>(UserInfo.registrationDatePropertyName).seconds,
                    )

                    ResultState.Success(info)
                }
        } else {
            flow { emit(ResultState.Failure()) }
        }

    }

    override suspend fun initSettings(email: String): Flow<ResultState<Unit>> =
        combine(
            updateRegistrationTime(),
            updateLastLogin(),
            updateName(email.substringBefore("@")),
            updateLocale(locale)
        ) { registerTimeResult, lastLoginResult, nameResult, localeResult ->
            val results = listOf(registerTimeResult, lastLoginResult, nameResult, localeResult)
            when {
                results.any { it is ResultState.Failure } -> {
                    results.first { it is ResultState.Failure }
                }

                results.any { it is ResultState.Loading } -> {
                    ResultState.Loading
                }

                results.all { it is ResultState.Success } -> {
                    ResultState.Success(Unit)
                }

                else -> {
                    ResultState.Loading
                }
            }
        }


    override suspend fun updateRegistrationTime() = flowRequest {
        val userId = Firebase.auth.currentUser?.uid
        val data = hashMapOf(UserInfo.registrationDatePropertyName to Timestamp.ServerTimestamp)

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .set(data, merge = true)
        }
    }

    override suspend fun updateLastLogin(): Flow<ResultState<Unit>> = flowRequest {
        val userId = Firebase.auth.currentUser?.uid
        val data = hashMapOf(UserInfo.lastLoginDatePropertyName to Timestamp.ServerTimestamp)

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .set(data, merge = true)
        }
    }

    override suspend fun updateLocale(locale: String): Flow<ResultState<Unit>> = flowRequest {
        val userId = Firebase.auth.currentUser?.uid
        val data = hashMapOf(UserInfo.localePropertyName to locale)

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .set(data, merge = true)
        }
    }

    override suspend fun updateName(newName: String): Flow<ResultState<Unit>> = flowRequest {
        val userId = Firebase.auth.currentUser?.uid
        val data = hashMapOf(UserInfo.displayNamePropertyName to newName)

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .set(data, merge = true)
        }
    }

    override suspend fun updateAvatarUrl(newUrl: String): Flow<ResultState<Unit>> = flowRequest {
        val userId = Firebase.auth.currentUser?.uid
        val data = hashMapOf(UserInfo.avatarUrlPropertyName to newUrl)

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .set(data, merge = true)
        }
    }
}