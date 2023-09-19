package com.velkonost.getbetter.shared.features.auth.presentation.di

import com.velkonost.getbetter.shared.features.auth.presentation.AuthViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val AuthPresentationPlatformModule = module {

    singleOf(::AuthViewModel)
}

@Suppress("unused")
val Koin.AuthViewModel: AuthViewModel
    get() = get()