package com.velkonost.getbetter.shared.features.settings.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface SettingsAction: UIContract.Action {

    data object NavigateBack : SettingsAction, SettingsNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateUp()
    }
}