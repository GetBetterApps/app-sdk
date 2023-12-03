package com.velkonost.getbetter.shared.features.taskdetail.presentation.di

import org.koin.dsl.module

val TaskDetailPresentationModule = module {
    includes(TaskDetailPresentationPlatformModule)
}