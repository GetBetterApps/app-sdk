package com.velkonost.getbetter.shared.features.detail.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.detail.presentation.contracts.Decrement
import com.velkonost.getbetter.shared.features.detail.presentation.contracts.DetailAction
import com.velkonost.getbetter.shared.features.detail.presentation.contracts.DetailNavigation
import com.velkonost.getbetter.shared.features.detail.presentation.contracts.DetailViewState
import com.velkonost.getbetter.shared.features.detail.presentation.contracts.Increment

class DetailViewModel
internal constructor() :
    BaseViewModel<DetailViewState, DetailAction, DetailNavigation, Nothing>(
        initialState = DetailViewState()
    ) {

    override fun dispatch(action: DetailAction) = when (action) {
        is Increment -> obtainIncrement()
        is Decrement -> obtainDecrement()
    }

    private fun obtainIncrement() {
        val previousCount = viewState.value.count
        emit(viewState.value.copy(count = previousCount + 1))
    }

    private fun obtainDecrement() {
        val previousCount = viewState.value.count
        emit(viewState.value.copy(count = previousCount - 1))
    }
}