package com.velkonost.getbetter.shared.features.onboarding.api.di

import com.velkonost.getbetter.shared.features.onboarding.api.OnboardingRepository
import com.velkonost.getbetter.shared.features.onboarding.api.OnboardingRepositoryImpl
import org.koin.dsl.module

val OnboardingDataModule = module {

    single<OnboardingRepository> {
        OnboardingRepositoryImpl(get())
    }
}