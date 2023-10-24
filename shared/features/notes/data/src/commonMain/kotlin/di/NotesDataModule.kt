package di

import NotesRepositoryImpl
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import remote.NotesRemoteDataSource

val NotesDataModule = module {
    singleOf(::NotesRemoteDataSource)

    single<NotesRepository> {
        NotesRepositoryImpl(get(), get())
    }
}