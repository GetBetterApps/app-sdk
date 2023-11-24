package com.velkonost.getbetter.shared.features.splash.api

import com.velkonost.getbetter.shared.core.model.profile.UIMode

interface SplashRepository {

    suspend fun prepareSession()

    suspend fun isUserRegistrationDateSaved(): Boolean

    suspend fun saveUserRegistrationDate(value: Long)

    suspend fun getTheme(): UIMode
}