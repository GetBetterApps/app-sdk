package com.velkonost.getbetter.shared.features.createnote.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface CreateNewNoteEvent : UIContract.Event {
    data object CreatedSuccess : CreateNewNoteEvent
}