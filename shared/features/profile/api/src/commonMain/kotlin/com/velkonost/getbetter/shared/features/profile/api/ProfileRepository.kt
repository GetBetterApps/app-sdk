package com.velkonost.getbetter.shared.features.profile.api

interface ProfileRepository {

    suspend fun changeTheme(newState: UIM)
}