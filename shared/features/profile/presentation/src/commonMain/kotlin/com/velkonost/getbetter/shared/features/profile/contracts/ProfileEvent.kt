package com.velkonost.getbetter.shared.features.profile.contracts

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface ProfileEvent : UIContract.Event {

    data class ThemeChanged(val value: UIThemeMode) : ProfileEvent

}