package com.velkonost.getbetter.shared.features.notedetail.presentation.di

import com.velkonost.getbetter.shared.features.notedetail.presentation.NoteDetailViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val NoteDetailPresentationPlatformModule = module {
    factoryOf(::NoteDetailViewModel)
}

@Suppress("unused")
val Koin.NoteDetailViewModel: NoteDetailViewModel
    get() = get()