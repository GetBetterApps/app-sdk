package com.velkonost.getbetter.shared.features.taskdetail.presentation.di

import com.velkonost.getbetter.shared.features.taskdetail.presentation.TaskDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val TaskDetailPresentationPlatformModule = module {
    viewModelOf(::TaskDetailViewModel)
}