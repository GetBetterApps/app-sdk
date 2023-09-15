package com.velkonost.getbetter.shared.features.home.presentation.models

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class HomeViewState(
    val mainText: String = "",
    val count: Int = 0
) : UIContract.State