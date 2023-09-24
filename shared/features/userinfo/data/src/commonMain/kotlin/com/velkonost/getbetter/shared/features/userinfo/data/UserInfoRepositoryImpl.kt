package com.velkonost.getbetter.shared.features.userinfo.data

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.core.util.locale
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class UserInfoRepositoryImpl
constructor(
    private val db: FirebaseFirestore,
) : UserInfoRepository {

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
        val data = hashMapOf("registrationDate" to Timestamp.ServerTimestamp)

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .set(data, merge = true)
        }
    }

    override suspend fun updateLastLogin(): Flow<ResultState<Unit>> = flowRequest {
        val userId = Firebase.auth.currentUser?.uid
        val data = hashMapOf("lastLoginDate" to Timestamp.ServerTimestamp)

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .set(data, merge = true)
        }
    }

    override suspend fun updateLocale(locale: String): Flow<ResultState<Unit>> = flowRequest {
        val userId = Firebase.auth.currentUser?.uid
        val data = hashMapOf("locale" to locale)

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .set(data, merge = true)
        }
    }

    override suspend fun updateName(newName: String): Flow<ResultState<Unit>> = flowRequest {
        Firebase.auth.currentUser?.updateProfile(displayName = newName)
    }
}