package com.velkonost.getbetter.shared.features.areadetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class AreaDetailViewState(
    val isLoading: Boolean = false
) : UIContract.State