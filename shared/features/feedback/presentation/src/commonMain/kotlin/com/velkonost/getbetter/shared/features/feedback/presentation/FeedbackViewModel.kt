package com.velkonost.getbetter.shared.features.feedback.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.feedback.api.FeedbackRepository
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackAction
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackNavigation
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackViewState

class FeedbackViewModel internal constructor(
    private val feedbackRepository: FeedbackRepository
) : BaseViewModel<FeedbackViewState, FeedbackAction, FeedbackNavigation, Nothing>(
    initialState = FeedbackViewState()
) {
    override fun dispatch(action: FeedbackAction) = when (action) {
        else -> {

        }
    }

}