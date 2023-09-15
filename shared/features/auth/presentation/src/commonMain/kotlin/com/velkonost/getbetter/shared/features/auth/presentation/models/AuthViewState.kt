package com.velkonost.getbetter.shared.features.auth.presentation.models

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class AuthViewState(
    val email: String = "",
    val password: String = "",
    val isRegistering: Boolean = true,
    val isLoading: Boolean = false
) : UIContract.State