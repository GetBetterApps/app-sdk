package com.velkonost.getbetter.shared.features.profile.contracts

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

sealed interface ProfileAction : UIContract.Action

data object LogoutClick : ProfileAction

data object SignUpClick : ProfileAction

data object SettingsClick : ProfileAction

data object ContactUsClick : ProfileAction

data class ThemeChange(val value: UIThemeMode) : ProfileAction

data class AvatarSelected(val avatarContent: ByteArray) : ProfileAction

data class AvatarSelectedBase64(val avatarContent: String) : ProfileAction

data object SubscriptionClick : ProfileAction, ProfileNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateToRoute(
        route = NavigationScreen.SubscriptionNavScreen.route,
    )
}