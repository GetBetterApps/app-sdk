package com.velkonost.getbetter.shared.features.taskdetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.extension.encodeToString
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_ABILITY
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_IS_FAVORITE
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface TaskDetailNavigation : UIContract.Navigation {

    data class NavigateToAbilityDetail(val ability: Ability) : TaskDetailNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
            route = NavigationScreen.AbilityDetailsNavScreen.route,
            args = hashMapOf(
                Pair(ARG_ABILITY, ability.encodeToString()),
                Pair(ARG_IS_FAVORITE, false.encodeToString()),
            )
        )
    }

}