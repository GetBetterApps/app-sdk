package com.velkonost.getbetter.shared.features.auth.domain.di

import com.velkonost.getbetter.shared.features.auth.domain.RegisterEmailUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AuthDomainModule = module {
    singleOf(::RegisterEmailUseCase)
}