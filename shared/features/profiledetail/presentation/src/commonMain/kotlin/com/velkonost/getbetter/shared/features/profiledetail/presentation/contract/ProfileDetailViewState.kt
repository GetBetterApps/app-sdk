package com.velkonost.getbetter.shared.features.profiledetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class ProfileDetailViewState(
    val isLoading: Boolean = false
) : UIContract.State