package com.velkonost.getbetter.shared.features.home.presentation.di

import org.koin.dsl.module

val HomePresentationModule = module {
    includes(HomePresentationPlatformModule)
}