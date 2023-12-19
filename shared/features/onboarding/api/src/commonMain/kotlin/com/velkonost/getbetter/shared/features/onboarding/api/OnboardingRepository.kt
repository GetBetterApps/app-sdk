package com.velkonost.getbetter.shared.features.onboarding.api

import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {

    fun updateOnboardingState(): Flow<ResultState<Unit>>

}