package com.velkonost.getbetter.shared.features.areas.data.di

import AreasRepository
import com.velkonost.getbetter.shared.features.areas.data.AreasRepositoryImpl
import com.velkonost.getbetter.shared.features.areas.data.remote.AreasRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AreasDataModule = module {
    singleOf(::AreasRemoteDataSource)

    single<AreasRepository> {
        AreasRepositoryImpl(get(), get())
    }
}