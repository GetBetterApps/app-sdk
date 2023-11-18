package com.velkonost.getbetter.shared.umbrella

import com.velkonost.getbetter.shared.core.datastore.di.CoreDataStoreModule
import com.velkonost.getbetter.shared.core.network.di.CoreNetworkModule
import com.velkonost.getbetter.shared.core.util.di.CoreUtilModule
import com.velkonost.getbetter.shared.features.addarea.presentation.di.AddAreaPresentationModule
import com.velkonost.getbetter.shared.features.areadetail.presentation.di.AreaDetailPresentationModule
import com.velkonost.getbetter.shared.features.areas.data.di.AreasDataModule
import com.velkonost.getbetter.shared.features.auth.data.di.AuthDataModule
import com.velkonost.getbetter.shared.features.auth.domain.di.AuthDomainModule
import com.velkonost.getbetter.shared.features.auth.presentation.di.AuthPresentationModule
import com.velkonost.getbetter.shared.features.comments.data.di.CommentsDataModule
import com.velkonost.getbetter.shared.features.detail.presentation.di.DetailPresentationModule
import com.velkonost.getbetter.shared.features.diary.data.di.DiaryDataModule
import com.velkonost.getbetter.shared.features.diary.di.CalendarsPresentationModule
import com.velkonost.getbetter.shared.features.diary.di.DiaryPresentationModule
import com.velkonost.getbetter.shared.features.follows.data.di.FollowsDataModule
import com.velkonost.getbetter.shared.features.home.presentation.di.HomePresentationModule
import com.velkonost.getbetter.shared.features.likes.data.di.LikesDataModule
import com.velkonost.getbetter.shared.features.notedetail.presentation.di.NoteDetailPresentationModule
import com.velkonost.getbetter.shared.features.notes.data.di.NotesDataModule
import com.velkonost.getbetter.shared.features.presentation.di.WisdomPresentationModule
import com.velkonost.getbetter.shared.features.profile.data.di.ProfileDataModule
import com.velkonost.getbetter.shared.features.profile.di.ProfilePresentationModule
import com.velkonost.getbetter.shared.features.social.data.di.SocialDataModule
import com.velkonost.getbetter.shared.features.social.di.SocialPresentationModule
import com.velkonost.getbetter.shared.features.splash.data.di.SplashDataModule
import com.velkonost.getbetter.shared.features.splash.presentation.di.SplashPresentationModule
import com.velkonost.getbetter.shared.features.userinfo.data.di.UserInfoDataModule
import di.TasksDataModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

object PlatformSDK {

    fun init(
        modules: List<Module>? = null,
        appDeclaration: KoinAppDeclaration = {}
    ) = startKoin {
        appDeclaration()

        modules(
            CoreDataStoreModule,
            CoreNetworkModule,
            CoreUtilModule,

            AreasDataModule,
            NotesDataModule,
            TasksDataModule,
            UserInfoDataModule,
            LikesDataModule,
            CommentsDataModule,
            FollowsDataModule,

            AuthDataModule,
            AuthDomainModule,
            AuthPresentationModule,

            HomePresentationModule,
            DetailPresentationModule,

            SplashDataModule,
            SplashPresentationModule,

            SocialDataModule,
            SocialPresentationModule,

            DiaryDataModule,
            DiaryPresentationModule,

            AddAreaPresentationModule,
            AreaDetailPresentationModule,
            NoteDetailPresentationModule,
            CalendarsPresentationModule,
            WisdomPresentationModule,

            ProfileDataModule,
            ProfilePresentationModule,
        )

        modules?.let(::modules)
    }
}