package com.velkonost.getbetter.shared.features.diary.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface DiaryEvent : UIContract.Event {
    data object NewNoteCreatedSuccess : DiaryEvent
}

sealed interface CreateNewAreaEvent : DiaryEvent {
    data object CreatedSuccess : CreateNewAreaEvent
}

