package di

import TasksRepository
import TasksRepositoryImpl
import org.koin.dsl.module

val TasksDataModule = module {
    single<TasksRepository> {
        TasksRepositoryImpl()
    }
}
