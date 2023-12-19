package com.velkonost.getbetter.shared.features.splash.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.ONBOARDING_SHOWN
import com.velkonost.getbetter.shared.core.datastore.SELECTED_UI_MODE
import com.velkonost.getbetter.shared.core.datastore.USER_REGISTRATION_MILLIS
import com.velkonost.getbetter.shared.core.datastore.extension.clear
import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.features.splash.api.SplashRepository
import kotlinx.coroutines.flow.first

class SplashRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : SplashRepository {

    override suspend fun prepareSession() {
        localDataSource.clear()
    }

    override suspend fun shouldShowOnboarding(): Boolean =
        localDataSource.data.first()[ONBOARDING_SHOWN] != true

    override suspend fun isUserRegistrationDateSaved(): Boolean =
        localDataSource.data.first()[USER_REGISTRATION_MILLIS] != null

    override suspend fun saveUserRegistrationDate(value: Long) {
        localDataSource.edit { preferences ->
            preferences[USER_REGISTRATION_MILLIS] = value
        }
    }

    override suspend fun getTheme(): UIThemeMode {
        localDataSource.data.first()[SELECTED_UI_MODE]?.let { name ->
            return UIThemeMode.entries.first { it.name == name }
        }

        return UIThemeMode.SystemTheme
    }
}