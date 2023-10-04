package com.velkonost.getbetter.shared.features.addarea.presentation.di

import org.koin.dsl.module

val AddAreaPresentationModule = module {
    includes(AddAreaPresentationPlatformModule)
}