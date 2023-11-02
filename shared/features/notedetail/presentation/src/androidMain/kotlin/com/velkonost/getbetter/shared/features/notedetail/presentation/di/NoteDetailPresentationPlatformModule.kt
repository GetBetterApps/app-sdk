package com.velkonost.getbetter.shared.features.notedetail.presentation.di

import com.velkonost.getbetter.shared.features.notedetail.presentation.NoteDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val NoteDetailPresentationPlatformModule = module {
    viewModelOf(::NoteDetailViewModel)
}