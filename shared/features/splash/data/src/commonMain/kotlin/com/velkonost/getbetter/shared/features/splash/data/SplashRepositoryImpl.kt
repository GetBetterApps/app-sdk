package com.velkonost.getbetter.shared.features.splash.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.clear
import com.velkonost.getbetter.shared.features.splash.api.SplashRepository

class SplashRepositoryImpl
constructor(
    private val localDataSource: DataStore<Preferences>
) : SplashRepository {

    override suspend fun prepareSession() {
        localDataSource.clear()
    }
}