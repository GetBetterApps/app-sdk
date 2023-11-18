package com.velkonost.getbetter.shared.features.profiledetail.presentation.di

import com.velkonost.getbetter.shared.features.profiledetail.presentation.ProfileDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val ProfileDetailPresentationPlatformModule = module {
    viewModelOf(::ProfileDetailViewModel)
}