package com.velkonost.getbetter.shared.features.notes.data.di

import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.notes.data.NotesRepositoryImpl
import com.velkonost.getbetter.shared.features.notes.data.remote.NotesRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val NotesDataModule = module {
    singleOf(::NotesRemoteDataSource)

    single<NotesRepository> {
        NotesRepositoryImpl(get(), get())
    }
}