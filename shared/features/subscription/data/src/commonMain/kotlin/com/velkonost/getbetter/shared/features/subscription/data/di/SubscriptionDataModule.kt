package com.velkonost.getbetter.shared.features.subscription.data.di

import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import com.velkonost.getbetter.shared.features.subscription.data.SubscriptionRepositoryImpl
import com.velkonost.getbetter.shared.features.subscription.data.remote.SubscriptionRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SubscriptionDataModule = module {
    singleOf(::SubscriptionRemoteDataSource)

    single<SubscriptionRepository> {
        SubscriptionRepositoryImpl(get(), get())
    }
}