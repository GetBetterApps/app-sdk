package com.velkonost.getbetter.shared.features.abilities.presentation.di

import com.velkonost.getbetter.shared.features.abilities.presentation.AbilitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val AbilitiesPresentationPlatformModule = module {
    viewModelOf(::AbilitiesViewModel)
}