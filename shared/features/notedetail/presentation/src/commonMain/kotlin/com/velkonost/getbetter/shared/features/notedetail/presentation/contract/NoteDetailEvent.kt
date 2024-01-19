package com.velkonost.getbetter.shared.features.notedetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface NoteDetailEvent : UIContract.Event {
    data object DeleteSuccess : NoteDetailEvent

    data object EditSuccess : NoteDetailEvent

    data object HideSuccess : NoteDetailEvent
}