package com.velkonost.getbetter.shared.features.splash.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class SplashViewState(
    val startDestination: String? = null
) : UIContract.State