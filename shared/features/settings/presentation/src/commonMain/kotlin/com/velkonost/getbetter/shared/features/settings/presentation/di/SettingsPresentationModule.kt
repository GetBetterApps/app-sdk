package com.velkonost.getbetter.shared.features.settings.presentation.di

import org.koin.dsl.module

val SettingsPresentationModule = module {
    includes(SettingsPresentationPlatformModule)
}