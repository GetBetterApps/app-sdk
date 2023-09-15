package com.velkonost.getbetter.shared.features.home.presentation.di

import com.velkonost.getbetter.shared.features.home.presentation.HomeViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val HomePresentationPlatformModule = module {
    singleOf(::HomeViewModel)
}

@Suppress("unused")
val Koin.HomeViewModel: HomeViewModel
    get() = get()