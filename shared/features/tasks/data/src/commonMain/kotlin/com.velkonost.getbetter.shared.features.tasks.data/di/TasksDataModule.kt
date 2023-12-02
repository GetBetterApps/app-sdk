package com.velkonost.getbetter.shared.features.tasks.data.di

import com.velkonost.getbetter.shared.features.tasks.api.TasksRepository
import com.velkonost.getbetter.shared.features.tasks.data.TasksRepositoryImpl
import org.koin.dsl.module

val TasksDataModule = module {
    single<TasksRepository> {
        TasksRepositoryImpl()
    }
}
