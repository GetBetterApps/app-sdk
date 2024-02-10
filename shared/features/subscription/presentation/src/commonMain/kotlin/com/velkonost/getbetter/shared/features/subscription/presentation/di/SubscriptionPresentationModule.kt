package com.velkonost.getbetter.shared.features.subscription.presentation.di

import org.koin.dsl.module

val SubscriptionPresentationModule = module {
    includes(SubscriptionPresentationPlatformModule)
}