package com.velkonost.getbetter.shared.features.areadetail.presentation.di

import com.velkonost.getbetter.shared.features.areadetail.presentation.AreaDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val AreaDetailPresentationPlatformModule = module {
    viewModelOf(::AreaDetailViewModel)
}