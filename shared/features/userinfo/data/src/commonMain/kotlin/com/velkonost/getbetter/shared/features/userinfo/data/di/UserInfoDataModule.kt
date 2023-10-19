package com.velkonost.getbetter.shared.features.userinfo.data.di

import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import com.velkonost.getbetter.shared.features.userinfo.data.UserInfoRepositoryFirebaseImpl
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

val UserInfoDataModule = module {
    single<UserInfoRepository> {
        UserInfoRepositoryFirebaseImpl(
            db = Firebase.firestore
        )
    }
}