package com.velkonost.getbetter.shared.features.subscription.presentation.di

import com.velkonost.getbetter.shared.features.subscription.presentation.SubscriptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val SubscriptionPresentationPlatformModule = module {
    viewModelOf(::SubscriptionViewModel)
}