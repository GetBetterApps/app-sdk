package com.velkonost.getbetter.shared.features.social.di

import com.velkonost.getbetter.shared.features.social.SocialViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val SocialPresentationPlatformModule = module {
    viewModelOf(::SocialViewModel)
}