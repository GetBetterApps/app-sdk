package com.velkonost.getbetter.shared.features.presentation.di

import org.koin.dsl.module

val WisdomPresentationModule = module {
    includes(WisdomPresentationPlatformModule)
}