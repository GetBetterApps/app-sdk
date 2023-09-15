package com.velkonost.getbetter.shared.features.home.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.home.presentation.models.HomeAction
import com.velkonost.getbetter.shared.features.home.presentation.models.HomeNavigation
import com.velkonost.getbetter.shared.features.home.presentation.models.HomeViewState
import com.velkonost.getbetter.shared.features.home.presentation.models.NavigateToDetails

class HomeViewModel
internal constructor() :
    BaseViewModel<HomeViewState, HomeAction, HomeNavigation, Nothing>(
        initialState = HomeViewState()
    ) {


    override fun dispatch(action: HomeAction) = when(action) {
        is NavigateToDetails -> emit(action)
    }

}