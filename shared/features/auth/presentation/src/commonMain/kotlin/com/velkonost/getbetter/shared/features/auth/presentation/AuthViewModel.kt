package com.velkonost.getbetter.shared.features.auth.presentation

import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onFailure
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.auth.domain.RegisterEmailUseCase
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthAction
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthNavigation
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.gitlive.firebase.auth.FirebaseUser
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class AuthViewModel
internal constructor(
    private val authRepository: AuthRepository<FirebaseUser>,
    private val registerEmailUseCase: RegisterEmailUseCase
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
        is AuthAction.NavigateToMainFlow -> Unit
    }

    private fun switchAuth() {
        val prevValue = viewState.value.isRegistering
        emit(viewState.value.copy(isRegistering = !prevValue))
    }

    private fun loginAnonymous() {
        launchJob {
            authRepository.registerAnonymously().collect { result ->
                with(result) {
                    isLoading {
                        emit(viewState.value.copy(isLoading = it))
                    }
                    onSuccess {
                        emit(AuthAction.NavigateToMainFlow)
                    }
                    onFailure {
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
            authRepository.loginEmail(
                email = viewState.value.email,
                password = viewState.value.password
            ).collect { result ->
                with(result) {
                    isLoading {
                        emit(viewState.value.copy(isLoading = it))
                    }
                    onSuccess {
                        emit(AuthAction.NavigateToMainFlow)
                    }
                    onFailure {
                        val message = Message.Builder()
                            .id("login_email_failure")
                            .text(StringDesc.Resource(it.getEmailLoginError()))
                            .messageType(MessageType.SnackBar.Builder().build())
                            .build()
                        emit(message)
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
                        emit(AuthAction.NavigateToMainFlow)
                    }
                    onFailure {
                        val message = Message.Builder()
                            .id("register_email_failure")
                            .text(StringDesc.Resource(it.getEmailRegisterError()))
                            .messageType(MessageType.SnackBar.Builder().build())
                            .build()
                        emit(message)
                    }
                }
            }
        }

//        launchJob {
//            authRepository.registerEmail(
//                email = viewState.value.email,
//                password = viewState.value.password
//            ).collect { result ->
//                with(result) {
//                    isLoading {
//                        emit(viewState.value.copy(isLoading = it))
//                    }
//                    onSuccess {
//                        emit(AuthAction.NavigateToMainFlow)
//                    }
//                    onFailure {
//                        val message = Message.Builder()
//                            .id("register_email_failure")
//                            .text(StringDesc.Resource(it.getEmailRegisterError()))
//                            .messageType(MessageType.SnackBar.Builder().build())
//                            .build()
//                        emit(message)
//                    }
//                }
//            }
//        }
    }

    private fun obtainEmailChanged(value: String) {
        emit(viewState.value.copy(email = value))
    }

    private fun obtainPasswordChanged(value: String) {
        emit(viewState.value.copy(password = value))
    }

}