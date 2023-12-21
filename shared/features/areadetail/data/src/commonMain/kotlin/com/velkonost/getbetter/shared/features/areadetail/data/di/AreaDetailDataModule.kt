package com.velkonost.getbetter.shared.features.areadetail.data.di

import com.velkonost.getbetter.shared.features.areadetail.api.AreaDetailRepository
import com.velkonost.getbetter.shared.features.areadetail.data.AreaDetailRepositoryImpl
import org.koin.dsl.module

val AreaDetailDataModule = module {
    single<AreaDetailRepository> {
        AreaDetailRepositoryImpl(get())
    }
}