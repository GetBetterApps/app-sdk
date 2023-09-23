package com.velkonost.getbetter.shared.core.datastore.di

import org.koin.dsl.module

val CoreDataStoreModule = module {
    includes(DataStorePlatformModule)
}