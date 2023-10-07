package com.velkonost.getbetter.shared.features.areadetail.presentation.di

import com.velkonost.getbetter.shared.features.areadetail.presentation.AreaDetailViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val AreaDetailPresentationPlatformModule = module {
    factoryOf(::AreaDetailViewModel)
}

@Suppress("unused")
val Koin.AreaDetailViewModel: AreaDetailViewModel
    get() = get()