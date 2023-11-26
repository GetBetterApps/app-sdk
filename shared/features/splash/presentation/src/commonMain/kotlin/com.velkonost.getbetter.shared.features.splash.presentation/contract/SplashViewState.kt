package com.velkonost.getbetter.shared.features.splash.presentation.contract

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class SplashViewState(
    val startDestination: String? = null,
    val selectedTheme: UIThemeMode? = null
) : UIContract.State