package com.velkonost.getbetter.shared.features.splash.presentation.contract

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface SplashEvent : UIContract.Event {

    data class ChangeTheme(val value: UIThemeMode) : SplashEvent
}