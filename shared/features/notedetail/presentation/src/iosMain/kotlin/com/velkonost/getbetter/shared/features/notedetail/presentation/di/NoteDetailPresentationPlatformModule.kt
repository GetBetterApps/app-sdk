package com.velkonost.getbetter.shared.features.notedetail.presentation.di

import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.notedetail.presentation.NoteDetailViewModel
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val SAVED_STATE_HANDLE_NAMED_QUALIFIER = "NoteDetailViewModel-SavedStateHandle"

internal actual val NoteDetailPresentationPlatformModule = module {
    single(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)) {
        SavedStateHandle()
    }

    factory {
        NoteDetailViewModel(
            notesRepository = get(),
            areasRepository = get(),
            userInfoRepository = get(),
            likesRepository = get(),
            savedStateHandle = get(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER))
        )
    }
}

@Suppress("unused")
val Koin.NoteDetailViewModel: NoteDetailViewModel
    get() = get()