package com.velkonost.getbetter.shared.features.abilitydetails.presentation.di

import com.velkonost.getbetter.shared.features.abilitydetails.presentation.AbilityDetailsViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val AbilityDetailsPresentationPlatformModule = module {
    factoryOf(::AbilityDetailsViewModel)
}

@Suppress("unused")
val Koin.AbilityDetailsViewModel: AbilityDetailsViewModel
    get() = get()