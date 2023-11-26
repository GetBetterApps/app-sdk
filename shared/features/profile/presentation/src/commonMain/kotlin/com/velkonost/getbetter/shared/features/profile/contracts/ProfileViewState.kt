package com.velkonost.getbetter.shared.features.profile.contracts

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.core.model.user.ExperienceData
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class ProfileViewState(
    val isLoading: Boolean = false,
    val isLogoutLoading: Boolean = false,
    val userName: String = "",
    val avatarUrl: String? = null,
    val experienceData: ExperienceData? = null,
    val selectedTheme: UIThemeMode = UIThemeMode.SystemTheme
) : UIContract.State
