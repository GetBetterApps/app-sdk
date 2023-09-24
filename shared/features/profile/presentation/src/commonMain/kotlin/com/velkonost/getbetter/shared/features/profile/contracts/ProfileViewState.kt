package com.velkonost.getbetter.shared.features.profile.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class ProfileViewState(
    val isLoading: Boolean = false,
) : UIContract.State