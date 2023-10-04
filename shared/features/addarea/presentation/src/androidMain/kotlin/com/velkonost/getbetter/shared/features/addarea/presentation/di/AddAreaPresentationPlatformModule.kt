package com.velkonost.getbetter.shared.features.addarea.presentation.di

import com.velkonost.getbetter.shared.features.addarea.presentation.AddAreaViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val AddAreaPresentationPlatformModule = module {
    viewModelOf(::AddAreaViewModel)
}