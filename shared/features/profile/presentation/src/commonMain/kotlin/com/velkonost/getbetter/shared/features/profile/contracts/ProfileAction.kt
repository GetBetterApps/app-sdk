package com.velkonost.getbetter.shared.features.profile.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface ProfileAction : UIContract.Action

data object LogoutClick : ProfileAction

data class AvatarSelected(val avatarContent: ByteArray) : ProfileAction