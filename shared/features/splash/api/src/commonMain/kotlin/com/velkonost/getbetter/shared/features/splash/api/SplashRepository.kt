package com.velkonost.getbetter.shared.features.splash.api

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode

interface SplashRepository {

    suspend fun prepareSession()

    suspend fun isUserRegistrationDateSaved(): Boolean

    suspend fun shouldShowOnboarding(): Boolean

    suspend fun saveUserRegistrationDate(value: Long)

    suspend fun getTheme(): UIThemeMode
}