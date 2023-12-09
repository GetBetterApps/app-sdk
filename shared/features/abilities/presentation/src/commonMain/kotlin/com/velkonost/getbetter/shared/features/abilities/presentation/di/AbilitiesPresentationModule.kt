package com.velkonost.getbetter.shared.features.abilities.presentation.di

import org.koin.dsl.module

val AbilitiesPresentationModule = module {
    includes(AbilitiesPresentationPlatformModule)
}