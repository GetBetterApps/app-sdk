package com.velkonost.getbetter.shared.features.createnote.data.di

import com.velkonost.getbetter.shared.features.createnote.api.CreateNoteRepository
import com.velkonost.getbetter.shared.features.createnote.data.CreateNoteRepositoryImpl
import org.koin.dsl.module

val CreateNoteDataModule = module {
    single<CreateNoteRepository> {
        CreateNoteRepositoryImpl(get())
    }
}