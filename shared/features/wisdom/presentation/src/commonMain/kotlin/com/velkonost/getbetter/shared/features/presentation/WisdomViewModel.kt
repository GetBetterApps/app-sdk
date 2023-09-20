package com.velkonost.getbetter.shared.features.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.presentation.models.WisdomAction
import com.velkonost.getbetter.shared.features.presentation.models.WisdomItemClick
import com.velkonost.getbetter.shared.features.presentation.models.WisdomNavigation
import com.velkonost.getbetter.shared.features.presentation.models.WisdomViewState

class WisdomViewModel
internal constructor(

) : BaseViewModel<WisdomViewState, WisdomAction, WisdomNavigation, Nothing>(
    initialState = WisdomViewState()
) {
    override fun dispatch(action: WisdomAction) = when(action) {
        is WisdomItemClick -> obtainWisdomItemClick(action.item)
        else -> {}
    }

    private fun obtainWisdomItemClick(item: WisdomItem) {

    }

}