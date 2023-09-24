package com.velkonost.getbetter.shared.features.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.presentation.model.WisdomType

sealed interface WisdomAction: UIContract.Action

data class WisdomItemClick(val item: WisdomType) : WisdomAction