package di

import TasksRepository
import TasksRepositoryImpl
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

val TasksDataModule = module {
    single<TasksRepository> {
        TasksRepositoryImpl(
            db = Firebase.firestore
        )
    }
}