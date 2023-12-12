package com.velkonost.getbetter.shared.features.abilities.presentation.contract

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_NOTE
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface AbilitiesNavigation : UIContract.Navigation {
    data class NavigateToAbilityDetail(val ability: Ability) : AbilitiesNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
            route = NavigationScreen.AbilityDetailsNavScreen.route,
            args = hashMapOf(
                Pair(ARG_NOTE, ability.encodeToString())
            )
        )
    }
}