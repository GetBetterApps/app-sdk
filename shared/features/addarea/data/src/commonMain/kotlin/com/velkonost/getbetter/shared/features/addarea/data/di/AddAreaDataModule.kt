package com.velkonost.getbetter.shared.features.addarea.data.di

import com.velkonost.getbetter.shared.features.addarea.api.AddAreaRepository
import com.velkonost.getbetter.shared.features.addarea.data.AddAreaRepositoryImpl
import org.koin.dsl.module

val AddAreaDataModule = module {

    single<AddAreaRepository> {
        AddAreaRepositoryImpl(get())
    }
}