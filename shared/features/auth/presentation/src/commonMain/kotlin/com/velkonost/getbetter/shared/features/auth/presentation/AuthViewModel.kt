package com.velkonost.getbetter.shared.features.auth.presentation

import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import com.velkonost.getbetter.shared.features.auth.domain.LoginAnonymousUseCase
import com.velkonost.getbetter.shared.features.auth.domain.LoginEmailUseCase
import com.velkonost.getbetter.shared.features.auth.domain.RegisterEmailUseCase
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthAction
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthNavigation
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthViewState
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.NavigateToMainFlow
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class AuthViewModel
internal constructor(
    private val registerEmailUseCase: RegisterEmailUseCase,
    private val loginEmailUseCase: LoginEmailUseCase,
    private val loginAnonymousUseCase: LoginAnonymousUseCase
) : BaseViewModel<AuthViewState, AuthAction, AuthNavigation, Nothing>(
    initialState = AuthViewState()
) {

    override fun dispatch(action: AuthAction) = when (action) {
        is AuthAction.EmailChanged -> obtainEmailChanged(action.value)
        is AuthAction.PasswordChanged -> obtainPasswordChanged(action.value)
        is AuthAction.LoginClick -> {
            if (viewState.value.isRegistering) registerEmail()
            else loginEmail()
        }

        is AuthAction.AnonymousLoginClick -> loginAnonymous()
        is AuthAction.SwitchAuthClick -> switchAuth()
    }

    private fun switchAuth() {
        val prevValue = viewState.value.isRegistering
        emit(viewState.value.copy(isRegistering = !prevValue))
    }

    private fun loginAnonymous() {
        launchJob {
            loginAnonymousUseCase()
                .collect { result ->
                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLoading = it))
                        }
                        onSuccess {
                            emit(NavigateToMainFlow)
                        }
                        onFailureWithMsg { throwable, errorMsg ->
                            emit(viewState.value.copy(isLoading = false))

                            val message = Message.Builder()
                                .id("login_anonymous_failure")
                                .text(StringDesc.Resource(SharedR.strings.auth_login_anonymous_failure))
                                .messageType(MessageType.SnackBar.Builder().build())
                                .build()
                            emit(message)
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
            ).collect { result ->
                with(result) {
                    isLoading {
                        emit(viewState.value.copy(isLoading = it))
                    }
                    onSuccess {
                        emit(NavigateToMainFlow)
                    }
                    onFailureWithMsg { _, message ->
                        message?.let { emit(it) }
                    }
                }
            }
        }
    }

    private fun registerEmail() {
        launchJob {
            registerEmailUseCase(
                email = viewState.value.email,
                password = viewState.value.password
            ).collect { result ->
                with(result) {
                    isLoading {
                        emit(viewState.value.copy(isLoading = it))
                    }
                    onSuccess {
                        emit(NavigateToMainFlow)
                    }
                    onFailureWithMsg { _, message ->
                        message?.let { emit(it) }
                    }
                }
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