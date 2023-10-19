package com.velkonost.getbetter.shared.features.splash.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.splash.presentation.contract.NavigateToAuth
import com.velkonost.getbetter.shared.features.splash.presentation.contract.NavigateToMainFlow
import com.velkonost.getbetter.shared.features.splash.presentation.contract.SplashAction
import com.velkonost.getbetter.shared.features.splash.presentation.contract.SplashNavigation
import com.velkonost.getbetter.shared.features.splash.presentation.contract.SplashViewState
import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import kotlinx.coroutines.delay

class SplashViewModel
internal constructor(
    private val authRepository: AuthRepository<UserInfo>
) : BaseViewModel<SplashViewState, SplashAction, SplashNavigation, Nothing>(
    initialState = SplashViewState()
) {

    init {
        setupStartDestination()
    }

    private fun setupStartDestination() {
        launchJob {
            delay(500)
            val isLoggedIn = authRepository.isUserLoggedIn()
            val startDestination = if (isLoggedIn) NavigateToMainFlow
            else NavigateToAuth

            emit(startDestination)
        }
    }

    override fun dispatch(action: SplashAction) = when (action) {
        else -> {

        }
    }

}