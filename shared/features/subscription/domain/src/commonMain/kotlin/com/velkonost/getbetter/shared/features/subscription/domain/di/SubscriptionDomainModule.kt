package com.velkonost.getbetter.shared.features.subscription.domain.di

import com.velkonost.getbetter.shared.features.subscription.domain.CheckSubscriptionAvailableUseCase
import com.velkonost.getbetter.shared.features.subscription.domain.CheckSubscriptionUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SubscriptionDomainModule = module {
    singleOf(::CheckSubscriptionUseCase)
    singleOf(::CheckSubscriptionAvailableUseCase)
}