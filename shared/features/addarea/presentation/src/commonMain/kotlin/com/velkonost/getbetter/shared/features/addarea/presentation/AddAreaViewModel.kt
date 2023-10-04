package com.velkonost.getbetter.shared.features.addarea.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaAction
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaNavigation
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaViewState

class AddAreaViewModel
internal constructor(
    private val areasRepository: AreasRepository
) : BaseViewModel<AddAreaViewState, AddAreaAction, AddAreaNavigation, Nothing>(
    initialState = AddAreaViewState()
) {
    override fun dispatch(action: AddAreaAction) = when (action) {
        else -> {}
    }
}