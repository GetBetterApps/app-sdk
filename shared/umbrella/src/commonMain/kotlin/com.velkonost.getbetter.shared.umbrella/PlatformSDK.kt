package com.velkonost.getbetter.shared.umbrella

import com.velkonost.getbetter.shared.core.network.di.CoreNetworkModule
import com.velkonost.getbetter.shared.core.util.di.CoreUtilModule
import com.velkonost.getbetter.shared.features.auth.data.di.AuthDataModule
import com.velkonost.getbetter.shared.features.auth.presentation.di.AuthPresentationModule
import com.velkonost.getbetter.shared.features.detail.presentation.di.DetailPresentationModule
import com.velkonost.getbetter.shared.features.home.presentation.di.HomePresentationModule
import com.velkonost.getbetter.shared.features.profile.di.ProfilePresentationModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object PlatformSDK {

    fun init(modules: List<Module>? = null) = startKoin {
        modules(
            CoreNetworkModule,
            CoreUtilModule,

            AuthDataModule,

            AuthPresentationModule,
            HomePresentationModule,
            DetailPresentationModule,
            ProfilePresentationModule
        )

        modules?.let(::modules)
    }
}