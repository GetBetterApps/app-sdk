package com.velkonost.getbetter.shared.features.feedback.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class FeedbackViewState(
    val isLoading: Boolean = false
) : UIContract.State