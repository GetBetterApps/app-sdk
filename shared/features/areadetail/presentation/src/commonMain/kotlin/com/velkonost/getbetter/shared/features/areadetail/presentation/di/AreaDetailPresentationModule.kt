package com.velkonost.getbetter.shared.features.areadetail.presentation.di

import org.koin.dsl.module

val AreaDetailPresentationModule = module {
    includes(AreaDetailPresentationPlatformModule)
}