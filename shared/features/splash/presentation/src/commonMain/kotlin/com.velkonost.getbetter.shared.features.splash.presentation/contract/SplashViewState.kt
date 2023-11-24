package com.velkonost.getbetter.shared.features.splash.presentation.contract

import com.velkonost.getbetter.shared.core.model.profile.UIMode
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class SplashViewState(
    val startDestination: String? = null,
    val selectedTheme: UIMode? = null
) : UIContract.State