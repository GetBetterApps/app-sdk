package com.velkonost.getbetter.shared.features.splash.presentation.di

import com.velkonost.getbetter.shared.features.splash.presentation.SplashViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val SplashPresentationPlatformModule = module {
    singleOf(::SplashViewModel)
}

@Suppress("unused")
val Koin.SplashViewModel: SplashViewModel
    get() = get()