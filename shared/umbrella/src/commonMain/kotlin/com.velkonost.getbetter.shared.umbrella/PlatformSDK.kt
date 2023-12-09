package com.velkonost.getbetter.shared.umbrella

import com.velkonost.getbetter.shared.core.datastore.di.CoreDataStoreModule
import com.velkonost.getbetter.shared.core.network.di.CoreNetworkModule
import com.velkonost.getbetter.shared.core.util.di.CoreUtilModule
import com.velkonost.getbetter.shared.features.abilities.data.di.AbilitiesDataModule
import com.velkonost.getbetter.shared.features.addarea.presentation.di.AddAreaPresentationModule
import com.velkonost.getbetter.shared.features.areadetail.presentation.di.AreaDetailPresentationModule
import com.velkonost.getbetter.shared.features.areas.data.di.AreasDataModule
import com.velkonost.getbetter.shared.features.auth.data.di.AuthDataModule
import com.velkonost.getbetter.shared.features.auth.domain.di.AuthDomainModule
import com.velkonost.getbetter.shared.features.auth.presentation.di.AuthPresentationModule
import com.velkonost.getbetter.shared.features.calendars.data.di.CalendarsDataModule
import com.velkonost.getbetter.shared.features.calendars.data.di.DiaryDataModule
import com.velkonost.getbetter.shared.features.calendars.presentation.DiaryPresentationModule
import com.velkonost.getbetter.shared.features.calendars.presentation.di.CalendarsPresentationModule
import com.velkonost.getbetter.shared.features.comments.data.di.CommentsDataModule
import com.velkonost.getbetter.shared.features.feedback.data.di.FeedbackDataModule
import com.velkonost.getbetter.shared.features.feedback.presentation.di.FeedbackPresentationModule
import com.velkonost.getbetter.shared.features.follows.data.di.FollowsDataModule
import com.velkonost.getbetter.shared.features.likes.data.di.LikesDataModule
import com.velkonost.getbetter.shared.features.notedetail.presentation.di.NoteDetailPresentationModule
import com.velkonost.getbetter.shared.features.notes.data.di.NotesDataModule
import com.velkonost.getbetter.shared.features.presentation.di.WisdomPresentationModule
import com.velkonost.getbetter.shared.features.profile.data.di.ProfileDataModule
import com.velkonost.getbetter.shared.features.profile.di.ProfilePresentationModule
import com.velkonost.getbetter.shared.features.profiledetail.presentation.di.ProfileDetailPresentationModule
import com.velkonost.getbetter.shared.features.settings.presentation.di.SettingsPresentationModule
import com.velkonost.getbetter.shared.features.social.data.di.SocialDataModule
import com.velkonost.getbetter.shared.features.social.di.SocialPresentationModule
import com.velkonost.getbetter.shared.features.splash.data.di.SplashDataModule
import com.velkonost.getbetter.shared.features.splash.presentation.di.SplashPresentationModule
import com.velkonost.getbetter.shared.features.taskdetail.presentation.di.TaskDetailPresentationModule
import com.velkonost.getbetter.shared.features.tasks.data.di.TasksDataModule
import com.velkonost.getbetter.shared.features.userinfo.data.di.UserInfoDataModule
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
            CalendarsDataModule,
            FeedbackDataModule,
            AbilitiesDataModule,

            AuthDataModule,
            AuthDomainModule,
            AuthPresentationModule,

            SplashDataModule,
            SplashPresentationModule,

            SocialDataModule,
            SocialPresentationModule,

            DiaryDataModule,
            DiaryPresentationModule,

            AddAreaPresentationModule,
            AreaDetailPresentationModule,
            ProfileDetailPresentationModule,
            NoteDetailPresentationModule,
            TaskDetailPresentationModule,
            CalendarsPresentationModule,
            WisdomPresentationModule,
            FeedbackPresentationModule,
            SettingsPresentationModule,

            ProfileDataModule,
            ProfilePresentationModule,
        )

        modules?.let(::modules)
    }
}