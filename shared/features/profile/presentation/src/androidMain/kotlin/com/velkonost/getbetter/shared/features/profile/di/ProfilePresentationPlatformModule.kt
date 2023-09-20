package com.velkonost.getbetter.shared.features.profile.di

import com.velkonost.getbetter.shared.features.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val ProfilePresentationPlatformModule = module {
    viewModelOf(::ProfileViewModel)
}