package com.velkonost.getbetter.shared.features.abilities.presentation.di

import com.velkonost.getbetter.shared.features.abilities.presentation.AbilitiesViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val AbilitiesPresentationPlatformModule = module {
    singleOf(::AbilitiesViewModel)
}

@Suppress("unused")
val Koin.AbilitiesViewModel: AbilitiesViewModel
    get() = get()