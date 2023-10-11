package com.velkonost.getbetter.shared.features.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.presentation.contracts.WisdomAction
import com.velkonost.getbetter.shared.features.presentation.contracts.WisdomItemClick
import com.velkonost.getbetter.shared.features.presentation.contracts.WisdomNavigation
import com.velkonost.getbetter.shared.features.presentation.contracts.WisdomViewState
import com.velkonost.getbetter.shared.features.presentation.model.WisdomType

class WisdomViewModel
internal constructor(

) : BaseViewModel<WisdomViewState, WisdomAction, WisdomNavigation, Nothing>(
    initialState = WisdomViewState()
) {

    init {
        emit(viewState.value)
    }

    override fun dispatch(action: WisdomAction) = when (action) {
        is WisdomItemClick -> obtainWisdomItemClick(action.item)
        else -> {}
    }

    private fun obtainWisdomItemClick(item: WisdomType) {
        TODO(item.toString())
    }

}