package com.velkonost.getbetter.shared.features.auth.domain

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

class LoginEmailUseCase constructor(
    private val authRepository: AuthRepository<FirebaseUser>,
    private val userInfoRepository: UserInfoRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<ResultState<Unit>> =
        authRepository.loginEmail(
            email = email,
            password = password
        ).flatMapMerge { loginResult ->
            when (loginResult) {
                is ResultState.Loading -> {
                    flow { emit(loginResult) }
                }

                is ResultState.Success -> {
                    userInfoRepository.updateLastLogin()
                }

                is ResultState.Failure -> {
                    flow { emit(loginResult) }
                }
            }
        }
}