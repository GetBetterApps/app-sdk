package com.velkonost.getbetter.shared.features.onboarding.presentation

import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.affirmations.api.AffirmationsRepository
import com.velkonost.getbetter.shared.features.auth.domain.LoginAnonymousUseCase
import com.velkonost.getbetter.shared.features.onboarding.api.OnboardingRepository
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingAction
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingEvent
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingNavigation
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class OnboardingViewModel
internal constructor(
    private val abilitiesRepository: AbilitiesRepository,
    private val affirmationsRepository: AffirmationsRepository,
    private val loginAnonymousUseCase: LoginAnonymousUseCase,
    private val onboardingRepository: OnboardingRepository
) : BaseViewModel<OnboardingViewState, OnboardingAction, OnboardingNavigation, OnboardingEvent>(
    initialState = OnboardingViewState()
) {

    init {
        fetchAbilities()
        fetchAffirmation()
    }

    override fun dispatch(action: OnboardingAction) = when (action) {
        is OnboardingAction.NextClick -> obtainNextClick()
        is OnboardingAction.SkipClick -> obtainSkipClick()
    }

    private fun fetchAbilities() {
        launchJob {
            abilitiesRepository.getAbilitiesForOnboarding() collectAndProcess {
                onSuccess { items ->
                    items?.let {
                        emit(viewState.value.copy(abilities = items))
                    }
                }
            }
        }
    }

    private fun fetchAffirmation() {
        launchJob {
            affirmationsRepository.getAffirmationForOnboarding() collectAndProcess {
                onSuccess { item ->
                    emit(viewState.value.copy(affirmation = item))
                }
            }
        }
    }

    private fun obtainSkipClick() {
        launchJob {
            onboardingRepository.updateOnboardingState()


//            emit(OnboardingNavigation.NavigateToAuth)
            loginAnonymousUseCase() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    emit(OnboardingNavigation.NavigateToAuth)
                }
            }
        }
    }

    private fun obtainNextClick() {
        val nextStep = viewState.value.step + 1
        if (nextStep >= 6) {
            obtainSkipClick()
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
