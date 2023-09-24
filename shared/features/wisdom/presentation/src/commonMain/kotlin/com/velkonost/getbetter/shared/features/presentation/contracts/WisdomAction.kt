package com.velkonost.getbetter.shared.features.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.presentation.WisdomItem

sealed interface WisdomAction: UIContract.Action

data class WisdomItemClick(val item: WisdomItem): WisdomAction