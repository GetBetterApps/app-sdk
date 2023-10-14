package com.velkonost.getbetter.shared.features.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.presentation.model.WisdomType

data class WisdomViewState(
    val isLoading: Boolean = false,
    val items: List<WisdomType> = WisdomType.values().toList()
) : UIContract.State