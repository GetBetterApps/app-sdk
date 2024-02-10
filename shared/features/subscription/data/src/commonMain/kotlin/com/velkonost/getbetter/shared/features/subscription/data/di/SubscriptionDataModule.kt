package com.velkonost.getbetter.shared.features.subscription.data.di

import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import com.velkonost.getbetter.shared.features.subscription.data.SubscriptionRepositoryImpl
import org.koin.dsl.module

val SubscriptionDataModule = module {
    single<SubscriptionRepository> {
        SubscriptionRepositoryImpl(get())
    }
}