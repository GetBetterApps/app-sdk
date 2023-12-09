package com.velkonost.getbetter.shared.features.abilities.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.abilities.presentation.contract.AbilitiesAction
import com.velkonost.getbetter.shared.features.abilities.presentation.contract.AbilitiesNavigation
import com.velkonost.getbetter.shared.features.abilities.presentation.contract.AbilitiesViewState

class AbilitiesViewModel
internal constructor(
    private val abilitiesRepository: AbilitiesRepository
) : BaseViewModel<AbilitiesViewState, AbilitiesAction, AbilitiesNavigation, Nothing>(
    initialState = AbilitiesViewState()
) {
    override fun dispatch(action: AbilitiesAction) = when (action) {
        else -> {

        }
    }

}