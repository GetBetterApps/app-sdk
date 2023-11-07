package com.velkonost.getbetter.shared.features.likes.data.di

import com.velkonost.getbetter.shared.features.likes.api.LikesRepository
import com.velkonost.getbetter.shared.features.likes.data.LikesRepositoryImpl
import com.velkonost.getbetter.shared.features.likes.data.remote.LikesRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val LikesDataModule = module {
    singleOf(::LikesRemoteDataSource)

    single<LikesRepository> {
        LikesRepositoryImpl(get(), get())
    }
}