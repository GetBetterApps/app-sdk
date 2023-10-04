package com.velkonost.getbetter.shared.features.addarea.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import model.Area

data class AddAreaViewState(
    val isLoading: Boolean = false,
    val items: List<Area> = emptyList()
) : UIContract.State