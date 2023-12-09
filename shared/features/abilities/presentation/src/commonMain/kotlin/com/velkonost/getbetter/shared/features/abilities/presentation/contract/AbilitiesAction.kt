package com.velkonost.getbetter.shared.features.abilities.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface AbilitiesAction : UIContract.Action {
    data object LoadNextPage : AbilitiesAction
}