package com.velkonost.getbetter.shared.features.notedetail.data.di

import com.velkonost.getbetter.shared.features.notedetail.api.NoteDetailRepository
import com.velkonost.getbetter.shared.features.notedetail.data.NoteDetailRepositoryImpl
import org.koin.dsl.module

val NoteDetailDataModule = module {
    single<NoteDetailRepository> {
        NoteDetailRepositoryImpl(get())
    }
}