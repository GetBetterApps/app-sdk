package com.velkonost.getbetter.shared.features.areadetail.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_AREA_DETAIL_SHOULD_SHOW
import com.velkonost.getbetter.shared.features.areadetail.api.AreaDetailRepository
import kotlinx.coroutines.flow.first

class AreaDetailRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : AreaDetailRepository {

    override suspend fun shouldShowHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_AREA_DETAIL_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_AREA_DETAIL_SHOULD_SHOW] = false
        }

        return value
    }
}