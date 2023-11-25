package com.velkonost.getbetter.shared.features.feedback.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface FeedbackAction : UIContract.Action

data object NavigateBack : FeedbackAction, FeedbackNavigation {
    override val event: NavigationEvent = NavigationEvent.NavigateUp()
}
