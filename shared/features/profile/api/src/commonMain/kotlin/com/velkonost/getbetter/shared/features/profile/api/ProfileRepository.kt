package com.velkonost.getbetter.shared.features.profile.api

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode

interface ProfileRepository {

    suspend fun changeTheme(newState: UIThemeMode)

    suspend fun getTheme(): UIThemeMode
}