package com.velkonost.getbetter.shared.features.settings.presentation.di

import com.velkonost.getbetter.shared.features.settings.presentation.SettingsViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val SettingsPresentationPlatformModule = module {
    factoryOf(::SettingsViewModel)
}

@Suppress("unused")
val Koin.SettingsViewModel: SettingsViewModel
    get() = get()