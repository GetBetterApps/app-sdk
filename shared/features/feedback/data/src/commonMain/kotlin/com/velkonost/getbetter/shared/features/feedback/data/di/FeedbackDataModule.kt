package com.velkonost.getbetter.shared.features.feedback.data.di

import com.velkonost.getbetter.shared.features.feedback.api.FeedbackRepository
import com.velkonost.getbetter.shared.features.feedback.data.FeedbackRepositoryImpl
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.FeedbackRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val FeedbackDataModule = module {
    singleOf(::FeedbackRemoteDataSource)

    single<FeedbackRepository> {
        FeedbackRepositoryImpl(get(), get())
    }
}