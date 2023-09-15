package com.velkonost.getbetter.shared.features.detail.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.detail.presentation.models.Decrement
import com.velkonost.getbetter.shared.features.detail.presentation.models.DetailAction
import com.velkonost.getbetter.shared.features.detail.presentation.models.DetailNavigation
import com.velkonost.getbetter.shared.features.detail.presentation.models.DetailViewState
import com.velkonost.getbetter.shared.features.detail.presentation.models.Increment

class DetailViewModel
internal constructor() :
    BaseViewModel<DetailViewState, DetailAction, DetailNavigation, Nothing>(
        initialState = DetailViewState()
    ) {

    override fun dispatch(action: DetailAction) = when(action) {
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