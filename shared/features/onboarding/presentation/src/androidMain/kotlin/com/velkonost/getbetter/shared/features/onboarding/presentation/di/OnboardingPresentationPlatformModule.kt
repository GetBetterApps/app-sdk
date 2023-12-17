package com.velkonost.getbetter.shared.features.onboarding.presentation.di

import com.velkonost.getbetter.shared.features.onboarding.presentation.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val OnboardingPresentationPlatformModule = module {
    viewModelOf(::OnboardingViewModel)
}