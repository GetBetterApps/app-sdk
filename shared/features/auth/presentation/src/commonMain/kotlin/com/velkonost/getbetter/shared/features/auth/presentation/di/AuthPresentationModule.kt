package com.velkonost.getbetter.shared.features.auth.presentation.di

import org.koin.dsl.module

val AuthPresentationModule = module {
    includes(AuthPresentationPlatformModule)
}
