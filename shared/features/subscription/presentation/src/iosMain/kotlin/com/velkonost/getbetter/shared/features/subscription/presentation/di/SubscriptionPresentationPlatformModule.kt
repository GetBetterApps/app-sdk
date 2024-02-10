package com.velkonost.getbetter.shared.features.subscription.presentation.di

import com.velkonost.getbetter.shared.features.subscription.presentation.SubscriptionViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val SubscriptionPresentationPlatformModule = module {
    factoryOf(::SubscriptionViewModel)
}

@Suppress("unused")
val Koin.SubscriptionViewModel: SubscriptionViewModel
    get() = get()