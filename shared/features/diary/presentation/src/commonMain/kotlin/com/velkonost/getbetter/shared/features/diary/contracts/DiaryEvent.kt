package com.velkonost.getbetter.shared.features.diary.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface DiaryEvent : UIContract.Event

sealed interface CreateNewAreaEvent : DiaryEvent {
    data object CreatedSuccess : CreateNewAreaEvent
}

sealed interface CreateNewNoteEvent : DiaryEvent {
    data object CreatedSuccess : CreateNewNoteEvent
}