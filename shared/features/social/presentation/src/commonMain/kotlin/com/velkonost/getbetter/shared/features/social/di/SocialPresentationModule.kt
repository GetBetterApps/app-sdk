package com.velkonost.getbetter.shared.features.social.di

import org.koin.dsl.module

val SocialPresentationModule = module {
    includes(SocialPresentationPlatformModule)
}