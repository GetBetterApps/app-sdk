package com.velkonost.getbetter.shared.features.auth.presentation

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onFailure
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.auth.presentation.models.AuthAction
import com.velkonost.getbetter.shared.features.auth.presentation.models.AuthNavigation
import com.velkonost.getbetter.shared.features.auth.presentation.models.AuthViewState
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collect

class AuthViewModel
internal constructor(
    private val authRepository: AuthRepository<FirebaseAuth>
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
                when(result) {
                    is ResultState.Success -> {
                        emit(viewState.value.copy(isLoading = false))
                        emit(AuthAction.NavigateToMainFlow)
//                        val message = Message.Builder()
//                            .id("1")
//                            .text("123")
//                            .messageType(MessageType.SnackBar.Builder().build())
//                            .build()
//                        emit(message)
                    }
                    is ResultState.Failure -> {
                        emit(viewState.value.copy(isLoading = false))
                    }
                    is ResultState.Loading -> {
                        emit(viewState.value.copy(isLoading = true))
                    }

                }
            }
        }
    }

    private fun loginEmail() {
        emit(viewState.value.copy(isLoading = true))
    }

    private fun registerEmail() {
        emit(viewState.value.copy(isLoading = true))


    }


    private fun obtainEmailChanged(value: String) {
        emit(viewState.value.copy(email = value))
    }

    private fun obtainPasswordChanged(value: String) {
        emit(viewState.value.copy(password = value))
    }

}