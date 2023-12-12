package com.velkonost.getbetter.shared.features.abilitydetails.presentation.di

import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.AbilityDetailsViewModel
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val SAVED_STATE_HANDLE_NAMED_QUALIFIER = "AbilityDetailsViewModel-SavedStateHandle"

internal actual val AbilityDetailsPresentationPlatformModule = module {
    single(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)) {
        SavedStateHandle()
    }

    factory {
        AbilityDetailsViewModel(
            savedStateHandle = get(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)),
            get(), get(), get()
        )
    }
}

@Suppress("unused")
val Koin.AbilityDetailsViewModel: AbilityDetailsViewModel
    get() = get()