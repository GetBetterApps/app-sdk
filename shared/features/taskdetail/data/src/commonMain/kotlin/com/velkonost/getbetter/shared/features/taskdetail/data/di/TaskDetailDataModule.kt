package com.velkonost.getbetter.shared.features.taskdetail.data.di

import com.velkonost.getbetter.shared.features.taskdetail.api.TaskDetailRepository
import com.velkonost.getbetter.shared.features.taskdetail.data.TaskDetailRepositoryImpl
import org.koin.dsl.module

val TaskDetailDataModule = module {
    single<TaskDetailRepository> {
        TaskDetailRepositoryImpl(get())
    }
}