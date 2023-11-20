package com.velkonost.getbetter.shared.features.calendars.di

import org.koin.dsl.module

val DiaryPresentationModule = module {
    includes(DiaryPresentationPlatformModule)
}