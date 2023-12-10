package com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class AbilityDetailsViewState(
    val isLoading: Boolean = false
) : UIContract.State