package com.velkonost.getbetter.shared.features.addarea.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface AddAreaAction : UIContract.Action

data object LoadNextPage : AddAreaAction

data class AddAreaClick(val areaId: String) : AddAreaAction