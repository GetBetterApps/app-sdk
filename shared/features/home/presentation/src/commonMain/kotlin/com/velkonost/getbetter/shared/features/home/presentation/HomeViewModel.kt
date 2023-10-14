package com.velkonost.getbetter.shared.features.home.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.home.presentation.contracts.HomeAction
import com.velkonost.getbetter.shared.features.home.presentation.contracts.HomeNavigation
import com.velkonost.getbetter.shared.features.home.presentation.contracts.HomeViewState
import com.velkonost.getbetter.shared.features.home.presentation.contracts.NavigateToDetails

class HomeViewModel
internal constructor() :
    BaseViewModel<HomeViewState, HomeAction, HomeNavigation, Nothing>(
        initialState = HomeViewState()
    ) {


    override fun dispatch(action: HomeAction) = when (action) {
        is NavigateToDetails -> emit(action)
    }

}