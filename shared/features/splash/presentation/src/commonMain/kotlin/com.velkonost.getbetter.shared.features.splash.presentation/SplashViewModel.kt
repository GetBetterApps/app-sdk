package com.velkonost.getbetter.shared.features.splash.presentation

import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.splash.api.SplashRepository
import com.velkonost.getbetter.shared.features.splash.presentation.contract.NavigateToAuth
import com.velkonost.getbetter.shared.features.splash.presentation.contract.NavigateToMainFlow
import com.velkonost.getbetter.shared.features.splash.presentation.contract.NavigateToOnboarding
import com.velkonost.getbetter.shared.features.splash.presentation.contract.SplashAction
import com.velkonost.getbetter.shared.features.splash.presentation.contract.SplashEvent
import com.velkonost.getbetter.shared.features.splash.presentation.contract.SplashNavigation
import com.velkonost.getbetter.shared.features.splash.presentation.contract.SplashViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import kotlinx.coroutines.delay

class SplashViewModel
internal constructor(
    private val authRepository: AuthRepository<String>,
    private val splashRepository: SplashRepository,
    private val userInfoRepository: UserInfoRepository
) : BaseViewModel<SplashViewState, SplashAction, SplashNavigation, SplashEvent>(
    initialState = SplashViewState()
) {

    init {
        startSession()
        setupStartDestination()
    }

    private fun startSession() {
        launchJob {
            emit(viewState.value.copy(selectedTheme = splashRepository.getTheme()))
            emit(SplashEvent.ChangeTheme(splashRepository.getTheme()))

            splashRepository.prepareSession()

            if (!splashRepository.isUserRegistrationDateSaved()) {
                userInfoRepository.fetchInfo() collectAndProcess {
                    onSuccess { userInfo ->
                        userInfo?.let {
                            updateUserRegistrationDate(it.registrationDate)
                        }
                    }
                }
            }
        }
    }

    private fun updateUserRegistrationDate(value: Long) {
        launchJob {
            splashRepository.saveUserRegistrationDate(value)
        }
    }

    private fun setupStartDestination() {
        launchJob {
            delay(500)
            val isLoggedIn = authRepository.isUserLoggedIn()
            val shouldShowOnboarding = splashRepository.shouldShowOnboarding()

            val startDestination = if (isLoggedIn) NavigateToMainFlow
            else if (shouldShowOnboarding) NavigateToOnboarding
            else NavigateToAuth

            emit(startDestination)
        }
    }

    override fun dispatch(action: SplashAction) = when (action) {
        is SplashAction.AllowSubscription -> obtainAllowSubscription(action.value)
    }

    private fun obtainAllowSubscription(value: Boolean) {
        launchJob {
            splashRepository.saveSubscriptionAllowanceState(value)
        }
    }

}