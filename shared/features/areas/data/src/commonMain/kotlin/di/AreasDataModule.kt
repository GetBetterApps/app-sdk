package di

import AreasRepository
import AreasRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import remote.AreasRemoteDataSource

val AreasDataModule = module {
    singleOf(::AreasRemoteDataSource)

    single<AreasRepository> {
        AreasRepositoryImpl(get(), get())
    }
}