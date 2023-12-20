package com.velkonost.getbetter.shared.features.addarea.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_ADD_AREA_SHOULD_SHOW
import com.velkonost.getbetter.shared.features.addarea.api.AddAreaRepository
import kotlinx.coroutines.flow.first

class AddAreaRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : AddAreaRepository {

    override suspend fun shouldShowHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_ADD_AREA_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_ADD_AREA_SHOULD_SHOW] = false
        }

        return value
    }

}