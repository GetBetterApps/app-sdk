package com.velkonost.getbetter.shared.features.presentation.di

import com.velkonost.getbetter.shared.features.presentation.WisdomViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


internal actual val WisdomPresentationPlatformModule = module {
    singleOf(::WisdomViewModel)
}

@Suppress("unused")
val Koin.WisdomViewModel: WisdomViewModel
    get() = get()