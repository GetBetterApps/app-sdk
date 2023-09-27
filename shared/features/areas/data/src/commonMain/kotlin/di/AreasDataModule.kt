package di

import AreasRepository
import AreasRepositoryImpl
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

val AreasDataModule = module {
    single<AreasRepository> {
        AreasRepositoryImpl(
            db = Firebase.firestore
        )
    }
}