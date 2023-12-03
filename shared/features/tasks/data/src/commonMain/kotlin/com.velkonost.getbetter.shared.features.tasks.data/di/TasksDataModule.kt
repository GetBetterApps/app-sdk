package com.velkonost.getbetter.shared.features.tasks.data.di

import com.velkonost.getbetter.shared.features.tasks.api.TasksRepository
import com.velkonost.getbetter.shared.features.tasks.data.TasksRepositoryImpl
import com.velkonost.getbetter.shared.features.tasks.data.remote.TasksRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val TasksDataModule = module {
    singleOf(::TasksRemoteDataSource)

    single<TasksRepository> {
        TasksRepositoryImpl(get(), get())
    }
}
