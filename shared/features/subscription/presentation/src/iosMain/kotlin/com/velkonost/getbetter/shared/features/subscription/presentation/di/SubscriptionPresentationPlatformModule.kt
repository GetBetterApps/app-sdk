package com.velkonost.getbetter.shared.features.subscription.presentation.di

import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.subscription.presentation.SubscriptionViewModel
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val SAVED_STATE_HANDLE_NAMED_QUALIFIER = "SubscriptionViewModel-SavedStateHandle"

internal actual val SubscriptionPresentationPlatformModule = module {
    single(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)) {
        SavedStateHandle()
    }

    factory {
        SubscriptionViewModel(
            savedStateHandle = get(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)),
            get(), get(), get()
        )
    }
}

@Suppress("unused")
val Koin.SubscriptionViewModel: SubscriptionViewModel
    get() = get()