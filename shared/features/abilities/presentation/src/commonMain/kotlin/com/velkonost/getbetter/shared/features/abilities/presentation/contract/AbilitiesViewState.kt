package com.velkonost.getbetter.shared.features.abilities.presentation.contract

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class AbilitiesViewState(
    val isLoading: Boolean = false,
    val items: List<Ability> = emptyList()
) : UIContract.State