package com.velkonost.getbetter.shared.features.onboarding.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class OnboardingViewState(
    val isLoading: Boolean = false
) : UIContract.State