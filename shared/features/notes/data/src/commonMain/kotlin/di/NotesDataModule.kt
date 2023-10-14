package di

import NotesRepositoryImpl
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

val NotesDataModule = module {
    single<NotesRepository> {
        NotesRepositoryImpl(
            db = Firebase.firestore
        )
    }
}