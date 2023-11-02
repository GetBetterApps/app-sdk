package com.velkonost.getbetter.shared.features.notedetail.presentation.di

import org.koin.dsl.module

val NoteDetailPresentationModule = module {
    includes(NoteDetailPresentationPlatformModule)
}