package com.velkonost.getbetter.shared.features.feedback.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface FeedbackEvent : UIContract.Event {

    data object NewFeedbackCreated : FeedbackEvent
}