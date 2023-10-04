package com.velkonost.getbetter.shared.features.addarea.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class AddAreaViewState(
    val isLoading: Boolean = false
) : UIContract.State