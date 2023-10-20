package com.velkonost.getbetter.shared.features.userinfo.data.di

import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import com.velkonost.getbetter.shared.features.userinfo.data.UserInfoRepositoryImpl
import org.koin.dsl.module

val UserInfoDataModule = module {
    single<UserInfoRepository> {
        UserInfoRepositoryImpl(get(), get())
    }
}