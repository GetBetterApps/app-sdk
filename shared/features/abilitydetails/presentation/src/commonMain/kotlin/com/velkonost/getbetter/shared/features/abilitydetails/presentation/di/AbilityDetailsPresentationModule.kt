package com.velkonost.getbetter.shared.features.abilitydetails.presentation.di

import org.koin.dsl.module

val AbilityDetailsPresentationModule = module {
    includes(AbilityDetailsPresentationPlatformModule)
}