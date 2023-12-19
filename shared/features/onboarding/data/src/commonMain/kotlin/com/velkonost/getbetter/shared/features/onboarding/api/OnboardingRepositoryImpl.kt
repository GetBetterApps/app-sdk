package com.velkonost.getbetter.shared.features.onboarding.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.ONBOARDING_SHOWN
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowLocalRequest
import kotlinx.coroutines.flow.Flow

class OnboardingRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : OnboardingRepository {

    override fun updateOnboardingState(): Flow<ResultState<Unit>> = flowLocalRequest {
        localDataSource.edit { preferences ->
            preferences[ONBOARDING_SHOWN] = true
        }
    }
}