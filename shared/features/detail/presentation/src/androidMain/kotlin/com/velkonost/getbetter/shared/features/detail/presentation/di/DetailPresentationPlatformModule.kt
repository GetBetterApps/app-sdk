package com.velkonost.getbetter.shared.features.detail.presentation.di

import com.velkonost.getbetter.shared.features.detail.presentation.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val DetailPresentationPlatformModule = module {
    viewModelOf(::DetailViewModel)
}