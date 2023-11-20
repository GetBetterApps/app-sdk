package com.velkonost.getbetter.shared.features.calendars.presentation.di

import org.koin.dsl.module

val CalendarsPresentationModule = module {
    includes(CalendarsPresentationPlatformModule)
}