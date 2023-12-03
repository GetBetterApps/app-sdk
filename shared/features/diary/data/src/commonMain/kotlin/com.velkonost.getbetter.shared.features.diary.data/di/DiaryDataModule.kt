package com.velkonost.getbetter.shared.features.calendars.data.di

import com.velkonost.getbetter.shared.features.calendars.data.DiaryRepositoryImpl
import com.velkonost.getbetter.shared.features.diary.api.DiaryRepository
import org.koin.dsl.module

val DiaryDataModule = module {
    single<DiaryRepository> {
        DiaryRepositoryImpl(get())
    }
}