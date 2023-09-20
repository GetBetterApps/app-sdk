package com.velkonost.getbetter.shared.features.presentation.models

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.presentation.WisdomItem

data class WisdomViewState(
    val isLoading: Boolean = false,
    val items: List<WisdomItem> = WisdomItem.values().toList()
): UIContract.State