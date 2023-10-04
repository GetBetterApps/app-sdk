package com.velkonost.getbetter.shared.features.addarea.presentation.di

import com.velkonost.getbetter.shared.features.addarea.presentation.AddAreaViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val AddAreaPresentationPlatformModule = module {
    factoryOf(::AddAreaViewModel)
}

@Suppress("unused")
val Koin.AddAreaViewModel: AddAreaViewModel
    get() = get()