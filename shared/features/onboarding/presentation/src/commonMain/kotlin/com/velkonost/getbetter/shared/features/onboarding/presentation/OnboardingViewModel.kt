package com.velkonost.getbetter.shared.features.onboarding.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingAction
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingEvent
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingNavigation
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingViewState

class OnboardingViewModel
internal constructor(
) : BaseViewModel<OnboardingViewState, OnboardingAction, OnboardingNavigation, OnboardingEvent>(
    initialState = OnboardingViewState()
) {
    override fun dispatch(action: OnboardingAction) = when (action) {
        else -> {

        }
    }

}
