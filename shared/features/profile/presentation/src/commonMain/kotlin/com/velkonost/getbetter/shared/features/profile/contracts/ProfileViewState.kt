package com.velkonost.getbetter.shared.features.profile.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class ProfileViewState(
    val isLoading: Boolean = false,
    val isLogoutLoading: Boolean = false,
    val userName: String = "",
    val avatarBytes: ByteArray? = null
) : UIContract.State