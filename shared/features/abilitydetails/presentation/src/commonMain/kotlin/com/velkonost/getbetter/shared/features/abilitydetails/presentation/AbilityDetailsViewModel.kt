package com.velkonost.getbetter.shared.features.abilitydetails.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsAction
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsNavigation
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsViewState
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository

class AbilityDetailsViewModel
internal constructor(
    private val notesRepository: NotesRepository,
    private val abilitiesRepository: AbilitiesRepository
) : BaseViewModel<AbilityDetailsViewState, AbilityDetailsAction, AbilityDetailsNavigation, Nothing>(
    initialState = AbilityDetailsViewState()
) {
    override fun dispatch(action: AbilityDetailsAction) = when (action) {
        else -> {

        }
    }

}
