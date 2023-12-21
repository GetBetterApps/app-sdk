package com.velkonost.getbetter.shared.features.taskdetail.presentation.di

import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.taskdetail.presentation.TaskDetailViewModel
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val SAVED_STATE_HANDLE_NAMED_QUALIFIER = "TaskDetailViewModel-SavedStateHandle"

internal actual val TaskDetailPresentationPlatformModule = module {
    single(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)) {
        SavedStateHandle()
    }

    factory {
        TaskDetailViewModel(
            savedStateHandle = get(named(SAVED_STATE_HANDLE_NAMED_QUALIFIER)),
            get(), get(), get(), get(), get(), get()
        )
    }
}

@Suppress("unused")
val Koin.TaskDetailViewModel: TaskDetailViewModel
    get() = get()