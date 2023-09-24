package com.velkonost.getbetter.shared.features.splash.presentation.di

import org.koin.dsl.module

val SplashPresentationModule = module {
    includes(SplashPresentationPlatformModule)
}