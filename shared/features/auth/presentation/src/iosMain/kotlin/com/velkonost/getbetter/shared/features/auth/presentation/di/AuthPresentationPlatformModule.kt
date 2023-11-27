package com.velkonost.getbetter.shared.features.auth.presentation.di

import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.auth.presentation.AuthViewModel
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val SAVED_STATE_HANDLE_NAMED_QUALIFIER = "AuthViewModel-SavedStateHandle"

internal actual val AuthPresentationPlatformModule = module {
    single(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)) {
        SavedStateHandle()
    }

    single {
        AuthViewModel(
            savedStateHandle = get(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)),
            get(), get(), get(), get()
        )
    }
}

@Suppress("unused")
val Koin.AuthViewModel: AuthViewModel
    get() = get()