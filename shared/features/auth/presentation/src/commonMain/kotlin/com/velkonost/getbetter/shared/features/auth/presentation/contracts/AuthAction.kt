package com.velkonost.getbetter.shared.features.auth.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface AuthAction : UIContract.Action {

    data class EmailChanged(val value: String) : AuthAction

    data class PasswordChanged(val value: String) : AuthAction

    data object LoginClick : AuthAction

    data object AnonymousLoginClick : AuthAction

    data object SwitchAuthClick : AuthAction

}