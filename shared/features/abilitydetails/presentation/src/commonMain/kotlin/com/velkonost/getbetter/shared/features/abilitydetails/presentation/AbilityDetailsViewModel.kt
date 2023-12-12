package com.velkonost.getbetter.shared.features.abilitydetails.presentation

import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsAction
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsNavigation
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsViewState
import com.velkonost.getbetter.shared.features.affirmations.api.AffirmationsRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import kotlinx.coroutines.Job

class AbilityDetailsViewModel
internal constructor(
    private val notesRepository: NotesRepository,
    private val abilitiesRepository: AbilitiesRepository,
    private val affirmationsRepository: AffirmationsRepository
) : BaseViewModel<AbilityDetailsViewState, AbilityDetailsAction, AbilityDetailsNavigation, Nothing>(
    initialState = AbilityDetailsViewState()
) {

    private val _notesPagingConfig = PagingConfig()
    private var notesLoadingJob: Job? = null

    override fun dispatch(action: AbilityDetailsAction) = when (action) {
        else -> {

        }
    }

    private fun fetchNotes() {
        launchJob {
            notesRepository.fetchNotesByAbility(

            )
        }
    }

}
