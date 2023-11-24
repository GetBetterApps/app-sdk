package com.velkonost.getbetter.shared.features.splash.presentation.contract

import com.velkonost.getbetter.shared.core.model.profile.UIMode
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface SplashEvent : UIContract.Event {

    data class ChangeTheme(val value: UIMode) : SplashEvent
}