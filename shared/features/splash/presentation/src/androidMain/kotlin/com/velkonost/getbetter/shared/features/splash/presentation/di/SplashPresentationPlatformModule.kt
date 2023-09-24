package com.velkonost.getbetter.shared.features.splash.presentation.di

import com.velkonost.getbetter.shared.features.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val SplashPresentationPlatformModule = module {
    viewModelOf(::SplashViewModel)
}