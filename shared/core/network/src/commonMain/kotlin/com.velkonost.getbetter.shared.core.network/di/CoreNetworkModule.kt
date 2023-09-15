package com.velkonost.getbetter.shared.core.network.di

import com.velkonost.getbetter.shared.core.network.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val CoreNetworkModule = module {
    singleOf(::KtorClient)
}