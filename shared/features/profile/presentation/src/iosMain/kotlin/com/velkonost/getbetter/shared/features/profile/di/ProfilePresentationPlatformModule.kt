package com.velkonost.getbetter.shared.features.profile.di

import com.velkonost.getbetter.shared.features.profile.ProfileViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val ProfilePresentationPlatformModule = module {
    singleOf(::ProfileViewModel)
}

@Suppress("unused")
val Koin.ProfileViewModel: ProfileViewModel
    get() = get()