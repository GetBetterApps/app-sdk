package com.velkonost.getbetter.shared.features.settings.presentation.di

import com.velkonost.getbetter.shared.features.settings.presentation.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val SettingsPresentationPlatformModule = module {
    viewModelOf(::SettingsViewModel)
}