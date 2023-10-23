package di

import AreasRepository
import AreasRepositoryFirebaseImpl
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

val AreasDataModule = module {
    single<AreasRepository> {
        AreasRepositoryFirebaseImpl(
            db = Firebase.firestore
        )
    }
}