package com.velkonost.getbetter.shared.features.diary.presentation.di

import org.koin.dsl.module

val DiaryPresentationModule = module {
    includes(DiaryPresentationPlatformModule)
}