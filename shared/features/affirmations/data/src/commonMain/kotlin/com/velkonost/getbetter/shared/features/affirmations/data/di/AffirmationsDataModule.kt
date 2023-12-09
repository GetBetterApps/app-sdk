package com.velkonost.getbetter.shared.features.affirmations.data.di

import com.velkonost.getbetter.shared.features.affirmations.api.AffirmationsRepository
import com.velkonost.getbetter.shared.features.affirmations.data.AffirmationsRepositoryImpl
import com.velkonost.getbetter.shared.features.affirmations.data.remote.AffirmationsRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AffirmationsDataModule = module {
    singleOf(::AffirmationsRemoteDataSource)

    single<AffirmationsRepository> {
        AffirmationsRepositoryImpl(get(), get())
    }
}