package com.velkonost.getbetter.shared.features.splash.api

interface SplashRepository {

    suspend fun prepareSession()

    suspend fun isUserRegistrationDateSaved(): Boolean

    suspend fun saveUserRegistrationDate(value: Long)
}