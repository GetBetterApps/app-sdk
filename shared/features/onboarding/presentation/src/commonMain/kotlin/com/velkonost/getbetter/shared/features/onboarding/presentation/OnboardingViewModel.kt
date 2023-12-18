package com.velkonost.getbetter.shared.features.onboarding.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingAction
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingEvent
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingNavigation
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class OnboardingViewModel
internal constructor(
    private val abilitiesRepository: AbilitiesRepository
) : BaseViewModel<OnboardingViewState, OnboardingAction, OnboardingNavigation, OnboardingEvent>(
    initialState = OnboardingViewState()
) {
    override fun dispatch(action: OnboardingAction) = when (action) {
        is OnboardingAction.NextClick -> obtainNextClick()
        else -> {

        }
    }

    private fun fetchAbilities() {
        launchJob {
            abilitiesRepository.getAll(
                page = 0,
                pageSize = 10
            )
        }
    }

    private fun obtainNextClick() {
        val nextStep = viewState.value.step + 1
        if (nextStep == 5) {

        } else {
            emit(
                viewState.value.copy(
                    step = nextStep,
                    title = nextStep.stepTitle()
                )
            )
        }
    }

    private fun Int.stepTitle() =
        StringDesc.Resource(
            when (this) {
                1 -> SharedR.strings.onboarding_step_1
                2 -> SharedR.strings.onboarding_step_2
                3 -> SharedR.strings.onboarding_step_3
                4 -> SharedR.strings.onboarding_step_4
                else -> SharedR.strings.onboarding_step_5
            }
        )
    

}
