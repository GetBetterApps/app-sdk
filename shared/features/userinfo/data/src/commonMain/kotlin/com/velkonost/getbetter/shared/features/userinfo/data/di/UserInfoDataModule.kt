package com.velkonost.getbetter.shared.features.userinfo.data.di

import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import com.velkonost.getbetter.shared.features.userinfo.data.UserInfoRepositoryImpl
import com.velkonost.getbetter.shared.features.userinfo.data.remote.UserInfoRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val UserInfoDataModule = module {
    singleOf(::UserInfoRemoteDataSource)

    single<UserInfoRepository> {
        UserInfoRepositoryImpl(get(), get())
    }
}