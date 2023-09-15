package com.velkonost.getbetter.shared.features.home.presentation.di

import com.velkonost.getbetter.shared.features.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val HomePresentationPlatformModule = module {
    viewModelOf(::HomeViewModel)
}