package com.velkonost.getbetter.shared.features.calendars.data.di

import com.velkonost.getbetter.shared.features.calendars.api.CalendarsRepository
import com.velkonost.getbetter.shared.features.calendars.data.CalendarsRepositoryImpl
import com.velkonost.getbetter.shared.features.calendars.data.remote.CalendarsRemoteDataSource
import org.koin.dsl.module

val CalendarsDataModule = module {
    single { CalendarsRemoteDataSource(get()) }

    single<CalendarsRepository> {
        CalendarsRepositoryImpl(get())
    }
}