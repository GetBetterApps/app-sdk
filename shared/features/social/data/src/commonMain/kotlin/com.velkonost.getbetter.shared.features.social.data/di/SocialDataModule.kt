package com.velkonost.getbetter.shared.features.social.data.di

import com.velkonost.getbetter.shared.features.social.api.SocialRepository
import com.velkonost.getbetter.shared.features.social.data.SocialRepositoryImpl
import com.velkonost.getbetter.shared.features.social.data.remote.SocialRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SocialDataModule = module {
    singleOf(::SocialRemoteDataSource)

    single<SocialRepository> {
        SocialRepositoryImpl(get(), get())
    }
}