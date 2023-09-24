package com.velkonost.getbetter.shared.features.detail.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface DetailAction : UIContract.Action

data object Increment : DetailAction

data object Decrement : DetailAction