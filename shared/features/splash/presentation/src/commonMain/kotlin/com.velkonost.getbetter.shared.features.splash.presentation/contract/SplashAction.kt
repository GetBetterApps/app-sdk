package com.velkonost.getbetter.shared.features.splash.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface SplashAction : UIContract.Action {

    data class AllowSubscription(val value: Boolean) : SplashAction

}