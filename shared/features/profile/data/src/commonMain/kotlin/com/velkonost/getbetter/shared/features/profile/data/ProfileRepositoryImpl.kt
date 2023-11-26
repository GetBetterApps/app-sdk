package com.velkonost.getbetter.shared.features.profile.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.SELECTED_UI_MODE
import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.features.profile.api.ProfileRepository
import kotlinx.coroutines.flow.first

class ProfileRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : ProfileRepository {

    override suspend fun changeTheme(newState: UIThemeMode) {
        localDataSource.edit { preferences ->
            preferences[SELECTED_UI_MODE] = newState.name
        }
    }

    override suspend fun getTheme(): UIThemeMode {
        localDataSource.data.first()[SELECTED_UI_MODE]?.let { name ->
            return UIThemeMode.entries.first { it.name == name }
        }

        return UIThemeMode.SystemTheme
    }
}