package com.velkonost.getbetter.shared.features.diary.di

import org.koin.dsl.module

val DiaryPresentationModule = module {
    includes(DiaryPresentationPlatformModule)
}