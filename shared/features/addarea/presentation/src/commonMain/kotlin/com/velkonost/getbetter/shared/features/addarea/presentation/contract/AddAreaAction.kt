package com.velkonost.getbetter.shared.features.addarea.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface AddAreaAction : UIContract.Action

data object LoadNextPage : AddAreaAction

data class AddAreaClick(val areaId: String) : AddAreaAction

data object NavigateBack : AddAreaAction, AddAreaNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateUp()
}