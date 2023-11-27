package com.velkonost.getbetter.shared.features.settings.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class SettingsViewState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val changePasswordState: ChangePasswordState = ChangePasswordState()
) : UIContract.State

data class ChangePasswordState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val repeatedNewPassword: String = ""
)