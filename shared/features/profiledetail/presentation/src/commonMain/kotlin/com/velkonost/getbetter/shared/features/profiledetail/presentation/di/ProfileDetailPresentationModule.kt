package com.velkonost.getbetter.shared.features.profiledetail.presentation.di

import org.koin.dsl.module

val ProfileDetailPresentationModule = module {
    includes(ProfileDetailPresentationPlatformModule)
}