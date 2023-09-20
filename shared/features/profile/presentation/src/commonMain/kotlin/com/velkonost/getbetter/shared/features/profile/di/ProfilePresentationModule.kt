package com.velkonost.getbetter.shared.features.profile.di

import org.koin.dsl.module

val ProfilePresentationModule = module {
    includes(ProfilePresentationPlatformModule)
}