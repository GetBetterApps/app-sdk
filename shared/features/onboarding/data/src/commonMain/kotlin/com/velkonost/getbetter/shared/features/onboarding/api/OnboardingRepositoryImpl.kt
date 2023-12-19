package com.velkonost.getbetter.shared.features.onboarding.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.ONBOARDING_SHOWN

class OnboardingRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : OnboardingRepository {

    override suspend fun updateOnboardingState() {
        localDataSource.edit { preferences ->
            preferences[ONBOARDING_SHOWN] = true
        }
    }
}