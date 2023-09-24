package com.velkonost.getbetter.shared.umbrella

import com.velkonost.getbetter.shared.core.datastore.di.CoreDataStoreModule
import com.velkonost.getbetter.shared.core.network.di.CoreNetworkModule
import com.velkonost.getbetter.shared.core.util.di.CoreUtilModule
import com.velkonost.getbetter.shared.features.auth.data.di.AuthDataModule
import com.velkonost.getbetter.shared.features.auth.domain.di.AuthDomainModule
import com.velkonost.getbetter.shared.features.auth.presentation.di.AuthPresentationModule
import com.velkonost.getbetter.shared.features.detail.presentation.di.DetailPresentationModule
import com.velkonost.getbetter.shared.features.diary.di.CalendarsPresentationModule
import com.velkonost.getbetter.shared.features.diary.di.DiaryPresentationModule
import com.velkonost.getbetter.shared.features.home.presentation.di.HomePresentationModule
import com.velkonost.getbetter.shared.features.presentation.di.WisdomPresentationModule
import com.velkonost.getbetter.shared.features.profile.di.ProfilePresentationModule
import com.velkonost.getbetter.shared.features.social.di.SocialPresentationModule
import com.velkonost.getbetter.shared.features.splash.presentation.di.SplashPresentationModule
import com.velkonost.getbetter.shared.features.userinfo.data.di.UserInfoDataModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object PlatformSDK {

    fun init(modules: List<Module>? = null) = startKoin {
        modules(
            CoreDataStoreModule,
            CoreNetworkModule,
            CoreUtilModule,

            UserInfoDataModule,

            AuthDataModule,
            AuthDomainModule,
            AuthPresentationModule,

            HomePresentationModule,
            DetailPresentationModule,

            SplashPresentationModule,
            SocialPresentationModule,
            DiaryPresentationModule,
            CalendarsPresentationModule,
            WisdomPresentationModule,
            ProfilePresentationModule,
        )

        modules?.let(::modules)
    }
}