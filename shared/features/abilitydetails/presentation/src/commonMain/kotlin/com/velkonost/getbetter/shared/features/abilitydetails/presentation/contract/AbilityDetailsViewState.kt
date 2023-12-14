package com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.model.task.Affirmation
import com.velkonost.getbetter.shared.core.util.PrefetchDistanceValue
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.model.AbilityDetailsTab

data class AbilityDetailsViewState(
    val isLoading: Boolean = false,
    val tabs: List<AbilityDetailsTab> = AbilityDetailsTab.entries,
    val ability: Ability? = null,
    val affirmations: List<Affirmation> = emptyList(),
    val userNotesViewState: UserNotesViewState = UserNotesViewState()
) : UIContract.State

data class UserNotesViewState(
    val isLoading: Boolean = false,
    val items: List<Note> = emptyList(),
    val loadMorePrefetch: Int = PrefetchDistanceValue,
)