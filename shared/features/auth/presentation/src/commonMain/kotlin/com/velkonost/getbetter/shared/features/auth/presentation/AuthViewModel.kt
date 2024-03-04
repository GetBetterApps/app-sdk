package com.velkonost.getbetter.shared.features.auth.presentation

import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.auth.domain.LoginAnonymousUseCase
import com.velkonost.getbetter.shared.features.auth.domain.LoginEmailUseCase
import com.velkonost.getbetter.shared.features.auth.domain.RegisterEmailUseCase
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthAction
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthNavigation
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthViewState
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.NavigateToMainFlow
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.NavigateToPaywall
import kotlinx.coroutines.flow.collectLatest

class AuthViewModel
internal constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository<String>,
    private val registerEmailUseCase: RegisterEmailUseCase,
    private val loginEmailUseCase: LoginEmailUseCase,
    private val loginAnonymousUseCase: LoginAnonymousUseCase
) : BaseViewModel<AuthViewState, AuthAction, AuthNavigation, Nothing>(
    initialState = AuthViewState(),
    savedStateHandle = savedStateHandle
) {

    private val identifyAnonymous = savedStateHandle
        .identifyAnonymous
        .stateInWhileSubscribed(initialValue = false)

    private val showPaywallAfterLogin = savedStateHandle
        .showPaywallNext
        .stateInWhileSubscribed(initialValue = false)

    init {
        launchJob {
            identifyAnonymous.collectLatest {
                emit(viewState.value.copy(forceSignUp = it))
            }
        }

        fetchDocsLinks()
    }

    @Suppress("unused")
    fun onAppear() {
        launchJob {
            if (authRepository.checkNeedsResetState()) {
                emit(initialState)
            }
        }
    }

    override fun dispatch(action: AuthAction) = when (action) {
        is AuthAction.EmailChanged -> obtainEmailChanged(action.value)
        is AuthAction.PasswordChanged -> obtainPasswordChanged(action.value)
        is AuthAction.LoginClick -> {
            if (viewState.value.forceSignUp) obtainIdentifyAnonymous()
            else if (viewState.value.isRegistering) registerEmail()
            else loginEmail()
        }

        is AuthAction.AnonymousLoginClick -> loginAnonymous()
        is AuthAction.SwitchAuthClick -> switchAuth()
    }

    private fun fetchDocsLinks() {
        emit(
            viewState.value.copy(
                privacyLink = authRepository.getPrivacyLink(),
                termsLink = authRepository.getTermsLink()
            )
        )
    }

    private fun switchAuth() {
        val prevValue = viewState.value.isRegistering
        emit(viewState.value.copy(isRegistering = !prevValue))
    }

    private fun obtainIdentifyAnonymous() {
        launchJob {
            authRepository.identifyAnonymous(
                email = viewState.value.email,
                password = viewState.value.password
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    navigateNext()
                }
            }
        }
    }

    private fun loginAnonymous() {
        launchJob {
            if (viewState.value.forceSignUp) {
                navigateNext(allowPaywall = false)
            } else {
                loginAnonymousUseCase() collectAndProcess {
                    isLoading {
                        emit(viewState.value.copy(isLoading = it))
                    }
                    onSuccess {
                        navigateNext(allowPaywall = false)
                    }
                }
            }
        }
    }

    private fun loginEmail() {
        launchJob {
            loginEmailUseCase(
                email = viewState.value.email,
                password = viewState.value.password
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    navigateNext()
                }
            }
        }
    }

    private fun registerEmail() {
        launchJob {
            registerEmailUseCase(
                email = viewState.value.email,
                password = viewState.value.password
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    navigateNext()
                }
            }
        }
    }

    private fun navigateNext(allowPaywall: Boolean = true) {
        launchJob {
            showPaywallAfterLogin.collectLatest {
                emit(
                    if (it && allowPaywall) NavigateToPaywall
                    else NavigateToMainFlow
                )
            }
        }
    }

    private fun obtainEmailChanged(value: String) {
        emit(viewState.value.copy(email = value))
    }

    private fun obtainPasswordChanged(value: String) {
        emit(viewState.value.copy(password = value))
    }

}