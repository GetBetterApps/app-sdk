package com.velkonost.getbetter.shared.features.detail.presentation.di

import org.koin.dsl.module

val DetailPresentationModule = module {
    includes(DetailPresentationPlatformModule)
}
