package com.velkonost.getbetter.shared.features.settings.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface SettingsEvent: UIContract.Event {

    data object PasswordChanged : SettingsEvent

}