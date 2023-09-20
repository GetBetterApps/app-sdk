package com.velkonost.getbetter.shared.features.presentation.di

import com.velkonost.getbetter.shared.features.presentation.WisdomViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val WisdomPresentationPlatformModule = module {
    viewModelOf(::WisdomViewModel)
}