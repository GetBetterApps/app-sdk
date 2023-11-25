package com.velkonost.getbetter.shared.features.feedback.presentation.contract

import com.velkonost.getbetter.shared.core.model.feedback.Feedback
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class FeedbackViewState(
    val isLoading: Boolean = false,
    val items: List<Feedback> = emptyList()
) : UIContract.State