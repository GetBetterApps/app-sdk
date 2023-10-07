package com.velkonost.getbetter.shared.features.areadetail.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailAction
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailNavigation
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailViewState

class AreaDetailViewModel
internal constructor(
    private val areasRepository: AreasRepository
) : BaseViewModel<AreaDetailViewState, AreaDetailAction, AreaDetailNavigation, Nothing>(
    initialState = AreaDetailViewState()
) {
    override fun dispatch(action: AreaDetailAction) = when (action) {
        else -> {}
    }

}