package com.velkonost.getbetter.shared.features.auth.presentation.models

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface AuthAction : UIContract.Action {

    data class EmailChanged(val value: String) : AuthAction

    data class PasswordChanged(val value: String) : AuthAction

    data object LoginClick : AuthAction

    data object AnonymousLoginClick : AuthAction

    data object SwitchAuthClick : AuthAction

    data object NavigateToMainFlow : AuthAction, AuthNavigation {
        override val event: NavigationEvent = NavigationEvent.NavigateAndPopUpToRoute(
            route = NavigationScreen.SocialNavScreen.route,
            popUpTo = NavigationScreen.AuthNavScreen.route,
        )
    }

}