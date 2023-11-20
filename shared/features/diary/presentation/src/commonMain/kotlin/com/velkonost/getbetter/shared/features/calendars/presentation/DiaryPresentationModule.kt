package com.velkonost.getbetter.shared.features.calendars.presentation

import org.koin.dsl.module

val DiaryPresentationModule = module {
    includes(DiaryPresentationPlatformModule)
}