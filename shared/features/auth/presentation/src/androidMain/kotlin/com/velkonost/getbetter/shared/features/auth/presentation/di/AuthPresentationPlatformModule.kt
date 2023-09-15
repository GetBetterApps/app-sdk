package com.velkonost.getbetter.shared.features.auth.presentation.di

import com.velkonost.getbetter.shared.features.auth.presentation.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val AuthPresentationPlatformModule = module {
    viewModelOf(::AuthViewModel)
}