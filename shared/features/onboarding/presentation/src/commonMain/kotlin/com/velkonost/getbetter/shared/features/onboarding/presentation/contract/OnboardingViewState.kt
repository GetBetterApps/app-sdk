package com.velkonost.getbetter.shared.features.onboarding.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

data class OnboardingViewState(
    val isLoading: Boolean = false,
    val step: Int = 1,
    val title: StringDesc = StringDesc.Resource(SharedR.strings.onboarding_step_1)
) : UIContract.State