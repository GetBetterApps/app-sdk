package com.velkonost.getbetter.shared.features.onboarding.presentation.di

import org.koin.dsl.module

val OnboardingPresentationModule = module {
    includes(OnboardingPresentationPlatformModule)
}