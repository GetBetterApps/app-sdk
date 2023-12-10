package com.velkonost.getbetter.shared.features.abilitydetails.presentation.di

import com.velkonost.getbetter.shared.features.abilitydetails.presentation.AbilityDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val AbilityDetailsPresentationPlatformModule = module {
    viewModelOf(::AbilityDetailsViewModel)
}