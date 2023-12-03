package com.velkonost.getbetter.shared.features.taskdetail.presentation.di

import com.velkonost.getbetter.shared.features.taskdetail.presentation.TaskDetailViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val TaskDetailPresentationPlatformModule = module {
    factoryOf(::TaskDetailViewModel)
}

@Suppress("unused")
val Koin.TaskDetailViewModel: TaskDetailViewModel
    get() = get()