package com.velkonost.getbetter.shared.features.abilities.data.di

import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.abilities.data.AbilitiesRepositoryImpl
import com.velkonost.getbetter.shared.features.abilities.data.remote.AbilitiesRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AbilitiesDataModule = module {
    singleOf(::AbilitiesRemoteDataSource)

    single<AbilitiesRepository> {
        AbilitiesRepositoryImpl(get(), get())
    }
}