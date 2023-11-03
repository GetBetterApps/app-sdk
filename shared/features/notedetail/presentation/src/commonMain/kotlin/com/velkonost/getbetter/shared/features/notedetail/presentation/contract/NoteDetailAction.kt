package com.velkonost.getbetter.shared.features.notedetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface NoteDetailAction : UIContract.Action

data object NavigateBack : NoteDetailAction, NoteDetailNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateUp()
}

data object AreaChanged : NoteDetailAction