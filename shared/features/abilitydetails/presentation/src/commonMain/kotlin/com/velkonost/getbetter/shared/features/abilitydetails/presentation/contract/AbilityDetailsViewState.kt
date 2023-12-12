package com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.PrefetchDistanceValue
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class AbilityDetailsViewState(
    val isLoading: Boolean = false,
    val ability: Ability? = null,
    val userNotesViewState: UserNotesViewState = UserNotesViewState()
) : UIContract.State

data class UserNotesViewState(
    val isLoading: Boolean = false,
    val items: List<Note> = emptyList(),
    val loadMorePrefetch: Int = PrefetchDistanceValue,
)