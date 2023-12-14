package com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface AbilityDetailsAction : UIContract.Action {

    data object UserNotesLoadNextPage : AbilityDetailsAction

    data object NavigateBack : AbilityDetailsAction, AbilityDetailsNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateUp()
    }
}