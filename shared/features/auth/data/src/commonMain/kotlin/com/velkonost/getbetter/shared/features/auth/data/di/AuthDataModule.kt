package com.velkonost.getbetter.shared.features.auth.data.di

import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.auth.data.AuthRepositoryImpl
import com.velkonost.getbetter.shared.features.auth.data.remote.AuthRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AuthDataModule = module {
    singleOf(::AuthRemoteDataSource)

    single<AuthRepository<String>> {
        AuthRepositoryImpl(get(), get())
    }
}