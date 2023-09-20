package com.velkonost.getbetter.shared.features.presentation.di

import com.velkonost.getbetter.shared.features.presentation.WisdomViewModel
import org.koin.dsl.module

val WisdomPresentationModule = module {
    includes(WisdomPresentationPlatformModule)
}