package com.velkonost.getbetter.shared.features.feedback.presentation.di

import org.koin.dsl.module

val FeedbackPresentationModule = module {
    includes(FeedbackPresentationPlatformModule)
}