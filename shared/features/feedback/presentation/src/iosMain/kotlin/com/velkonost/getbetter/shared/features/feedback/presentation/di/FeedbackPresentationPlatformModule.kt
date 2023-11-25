package com.velkonost.getbetter.shared.features.feedback.presentation.di

import com.velkonost.getbetter.shared.features.feedback.presentation.FeedbackViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val FeedbackPresentationPlatformModule = module {
    singleOf(::FeedbackViewModel)
}

@Suppress("unused")
val Koin.FeedbackViewModel: FeedbackViewModel
    get() = get()