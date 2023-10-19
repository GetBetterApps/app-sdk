package com.velkonost.getbetter.shared.features.auth.data.di

import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.auth.data.AuthRepositoryFirebaseImpl
import dev.gitlive.firebase.auth.FirebaseUser
import org.koin.dsl.module

val AuthDataModule = module {
    single<AuthRepository<FirebaseUser>> {
        AuthRepositoryFirebaseImpl()
    }
}