package com.velkonost.getbetter.shared.features.auth.domain

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

class LoginAnonymousUseCase constructor(
    private val authRepository: AuthRepository<FirebaseUser>,
    private val userInfoRepository: UserInfoRepository
) {

    suspend operator fun invoke(): Flow<ResultState<Unit>> =
        authRepository.registerAnonymously()
            .flatMapMerge { registerResult ->
                when (registerResult) {
                    is ResultState.Loading -> {
                        flow { emit(registerResult) }
                    }

                    is ResultState.Success -> {
                        userInfoRepository.initSettings("")
                    }

                    is ResultState.Failure -> {
                        flow { emit(registerResult) }
                    }
                }
            }
}