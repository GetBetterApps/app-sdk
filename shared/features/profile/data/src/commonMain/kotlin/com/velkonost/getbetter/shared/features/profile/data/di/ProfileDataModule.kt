package com.velkonost.getbetter.shared.features.profile.data.di

import com.velkonost.getbetter.shared.features.profile.api.ProfileRepository
import com.velkonost.getbetter.shared.features.profile.data.ProfileRepositoryImpl
import org.koin.dsl.module

val ProfileDataModule = module {
    single<ProfileRepository> {
        ProfileRepositoryImpl(get())
    }
}