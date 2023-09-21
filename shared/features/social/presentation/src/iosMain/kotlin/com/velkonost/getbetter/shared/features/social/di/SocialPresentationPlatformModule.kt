package com.velkonost.getbetter.shared.features.social.di

import com.velkonost.getbetter.shared.features.social.SocialViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val SocialPresentationPlatformModule = module {
    singleOf(::SocialViewModel)
}

@Suppress("unused")
val Koin.SocialViewModel: SocialViewModel
    get() = get()