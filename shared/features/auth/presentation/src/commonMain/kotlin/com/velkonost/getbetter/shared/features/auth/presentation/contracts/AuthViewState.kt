package com.velkonost.getbetter.shared.features.auth.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class AuthViewState(
    val email: String = "",
    val password: String = "",
    val isRegistering: Boolean = true,
    val isLoading: Boolean = false,
    val forceSignUp: Boolean = false,
    val privacyLink: String = "",
    val termsLink: String = ""
) : UIContract.State