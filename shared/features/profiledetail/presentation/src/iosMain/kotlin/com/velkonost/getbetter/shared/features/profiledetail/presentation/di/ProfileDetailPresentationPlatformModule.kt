package com.velkonost.getbetter.shared.features.profiledetail.presentation.di

import com.velkonost.getbetter.shared.features.profiledetail.presentation.ProfileDetailViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val ProfileDetailPresentationPlatformModule = module {
    factoryOf(::ProfileDetailViewModel)
}

@Suppress("unused")
val Koin.ProfileDetailViewModel: ProfileDetailViewModel
    get() = get()