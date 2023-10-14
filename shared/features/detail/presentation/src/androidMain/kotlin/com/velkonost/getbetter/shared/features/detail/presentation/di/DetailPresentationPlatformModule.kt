package com.velkonost.getbetter.shared.features.detail.presentation.di

import com.velkonost.getbetter.shared.features.detail.presentation.DetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val DetailPresentationPlatformModule = module {
    singleOf(::DetailViewModel)
}