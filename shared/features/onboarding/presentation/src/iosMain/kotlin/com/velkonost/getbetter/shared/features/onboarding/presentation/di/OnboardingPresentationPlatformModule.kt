package com.velkonost.getbetter.shared.features.onboarding.presentation.di

import com.velkonost.getbetter.shared.features.onboarding.presentation.OnboardingViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val OnboardingPresentationPlatformModule = module {
    singleOf(::OnboardingViewModel)
}

@Suppress("unused")
val Koin.OnboardingViewModel: OnboardingViewModel
    get() = get()