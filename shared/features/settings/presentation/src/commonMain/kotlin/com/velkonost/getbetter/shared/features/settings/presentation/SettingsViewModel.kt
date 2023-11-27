package com.velkonost.getbetter.shared.features.settings.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsAction
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsEvent
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsNavigation
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository

class SettingsViewModel internal constructor(
    private val userInfoRepository: UserInfoRepository,
) : BaseViewModel<SettingsViewState, SettingsAction, SettingsNavigation, SettingsEvent>(
    initialState = SettingsViewState()
) {
    override fun dispatch(action: SettingsAction) = when(action) {
        else -> {

        }
    }

}