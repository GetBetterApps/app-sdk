package com.velkonost.getbetter.shared.features.profile.contracts

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface ProfileAction : UIContract.Action

data object LogoutClick : ProfileAction

data object ContactUsClick : ProfileAction

data class ThemeChange(val value: UIThemeMode) : ProfileAction

data class AvatarSelected(val avatarContent: ByteArray) : ProfileAction

data class AvatarSelectedBase64(val avatarContent: String) : ProfileAction