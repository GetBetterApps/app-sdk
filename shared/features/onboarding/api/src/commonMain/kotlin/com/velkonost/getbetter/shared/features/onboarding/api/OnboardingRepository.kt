package com.velkonost.getbetter.shared.features.onboarding.api

interface OnboardingRepository {

    suspend fun updateOnboardingState()

}