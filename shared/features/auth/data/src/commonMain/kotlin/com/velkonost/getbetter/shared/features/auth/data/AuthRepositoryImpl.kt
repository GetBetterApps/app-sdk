package com.velkonost.getbetter.shared.features.auth.data

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl : AuthRepository<FirebaseUser> {

    override suspend fun loginEmail(
        email: String,
        password: String
    ): Flow<ResultState<FirebaseUser>> = flowRequest {
        val result = Firebase.auth.signInWithEmailAndPassword(email, password)
        result.user!!
    }

    override suspend fun registerEmail(
        email: String,
        password: String
    ): Flow<ResultState<FirebaseUser>> = flowRequest {
        val result = Firebase.auth.createUserWithEmailAndPassword(email, password)
        result.user!!
    }

    override suspend fun registerAnonymously(): Flow<ResultState<FirebaseUser>> = flowRequest {
        val result = Firebase.auth.signInAnonymously()
        result.user!!
    }

    override fun isUserLoggedIn(): Boolean =
        Firebase.auth.currentUser != null

    override suspend fun logout() = flowRequest {
        Firebase.auth.signOut()
    }
}