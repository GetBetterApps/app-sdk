package com.velkonost.getbetter.shared.features.splash.data.di

import com.velkonost.getbetter.shared.features.splash.api.SplashRepository
import com.velkonost.getbetter.shared.features.splash.data.SplashRepositoryImpl
import org.koin.dsl.module

val SplashDataModule = module {
    single<SplashRepository> {
        SplashRepositoryImpl(get())
    }
}