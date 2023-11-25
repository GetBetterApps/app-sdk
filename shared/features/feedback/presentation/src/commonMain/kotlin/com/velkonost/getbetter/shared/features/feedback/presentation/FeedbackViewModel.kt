package com.velkonost.getbetter.shared.features.feedback.presentation

import com.velkonost.getbetter.shared.core.model.feedback.FeedbackType
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.feedback.api.FeedbackRepository
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackAction
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackNavigation
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackViewState
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.NewFeedbackAction

class FeedbackViewModel internal constructor(
    private val feedbackRepository: FeedbackRepository
) : BaseViewModel<FeedbackViewState, FeedbackAction, FeedbackNavigation, Nothing>(
    initialState = FeedbackViewState()
) {

    init {
        getFeedbacks()
    }

    override fun dispatch(action: FeedbackAction) = when (action) {
        is NavigateBack -> emit(action)
        is NewFeedbackAction.TypeChanged -> obtainNewFeedbackTypeChanged(action.value)
        is NewFeedbackAction.TextChanged -> obtainNewFeedbackTextChanged(action.value)
        else -> {

        }
    }

    private fun getFeedbacks() {
        launchJob {
            feedbackRepository.getList() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { items ->
                    items?.let {
                        emit(viewState.value.copy(items = items))
                    }
                }
            }
        }
    }

    private fun obtainNewFeedbackTypeChanged(value: FeedbackType) {
        val newFeedbackState = viewState.value.newFeedback.copy(type = value)
        emit(viewState.value.copy(newFeedback = newFeedbackState))
    }

    private fun obtainNewFeedbackTextChanged(value: String) {
        val newFeedbackState = viewState.value.newFeedback.copy(text = value)
        emit(viewState.value.copy(newFeedback = newFeedbackState))
    }


}