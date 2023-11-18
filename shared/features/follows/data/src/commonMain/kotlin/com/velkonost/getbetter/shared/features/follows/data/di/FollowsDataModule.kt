package com.velkonost.getbetter.shared.features.follows.data.di

import com.velkonost.getbetter.shared.features.follows.api.FollowsRepository
import com.velkonost.getbetter.shared.features.follows.data.FollowsRepositoryImpl
import com.velkonost.getbetter.shared.features.follows.data.remote.FollowsRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val FollowsDataModule = module {
    singleOf(::FollowsRemoteDataSource)

    single<FollowsRepository> {
        FollowsRepositoryImpl(get(), get())
    }
}