package com.velkonost.getbetter.shared.features.feedback.presentation.di

import com.velkonost.getbetter.shared.features.feedback.presentation.FeedbackViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val FeedbackPresentationPlatformModule = module {
    viewModelOf(::FeedbackViewModel)
}