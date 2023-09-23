package com.velkonost.getbetter.shared.core.datastore.di

import com.velkonost.getbetter.shared.core.datastore.dataStore
import org.koin.dsl.module

internal actual val DataStorePlatformModule = module {
    single { dataStore() }
}