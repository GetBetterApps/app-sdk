package com.velkonost.getbetter.shared.features.comments.data.di

import com.velkonost.getbetter.shared.features.comments.api.CommentsRepository
import com.velkonost.getbetter.shared.features.comments.data.CommentsRepositoryImpl
import com.velkonost.getbetter.shared.features.comments.data.remote.CommentsRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val CommentsDataModule = module {
    singleOf(::CommentsRemoteDataSource)

    single<CommentsRepository> {
        CommentsRepositoryImpl(get(), get())
    }
}