package com.velkonost.getbetter.shared.features.profile.api

import com.velkonost.getbetter.shared.core.model.profile.UIMode

interface ProfileRepository {

    suspend fun changeTheme(newState: UIMode)

    suspend fun getTheme(): UIMode
}