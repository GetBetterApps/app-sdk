package com.velkonost.getbetter.shared.umbrella

import com.velkonost.getbetter.shared.core.datastore.di.CoreDataStoreModule
import com.velkonost.getbetter.shared.core.network.di.CoreNetworkModule
import com.velkonost.getbetter.shared.core.util.di.CoreUtilModule
import com.velkonost.getbetter.shared.features.abilities.data.di.AbilitiesDataModule
import com.velkonost.getbetter.shared.features.abilities.presentation.di.AbilitiesPresentationModule
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.di.AbilityDetailsPresentationModule
import com.velkonost.getbetter.shared.features.addarea.data.di.AddAreaDataModule
import com.velkonost.getbetter.shared.features.addarea.presentation.di.AddAreaPresentationModule
import com.velkonost.getbetter.shared.features.affirmations.data.di.AffirmationsDataModule
import com.velkonost.getbetter.shared.features.areadetail.data.di.AreaDetailDataModule
import com.velkonost.getbetter.shared.features.areadetail.presentation.di.AreaDetailPresentationModule
import com.velkonost.getbetter.shared.features.areas.data.di.AreasDataModule
import com.velkonost.getbetter.shared.features.auth.data.di.AuthDataModule
import com.velkonost.getbetter.shared.features.auth.domain.di.AuthDomainModule
import com.velkonost.getbetter.shared.features.auth.presentation.di.AuthPresentationModule
import com.velkonost.getbetter.shared.features.calendars.data.di.CalendarsDataModule
import com.velkonost.getbetter.shared.features.calendars.data.di.DiaryDataModule
import com.velkonost.getbetter.shared.features.calendars.presentation.di.CalendarsPresentationModule
import com.velkonost.getbetter.shared.features.comments.data.di.CommentsDataModule
import com.velkonost.getbetter.shared.features.createnote.data.di.CreateNoteDataModule
import com.velkonost.getbetter.shared.features.diary.presentation.di.DiaryPresentationModule
import com.velkonost.getbetter.shared.features.feedback.data.di.FeedbackDataModule
import com.velkonost.getbetter.shared.features.feedback.presentation.di.FeedbackPresentationModule
import com.velkonost.getbetter.shared.features.follows.data.di.FollowsDataModule
import com.velkonost.getbetter.shared.features.likes.data.di.LikesDataModule
import com.velkonost.getbetter.shared.features.notedetail.data.di.NoteDetailDataModule
import com.velkonost.getbetter.shared.features.notedetail.presentation.di.NoteDetailPresentationModule
import com.velkonost.getbetter.shared.features.notes.data.di.NotesDataModule
import com.velkonost.getbetter.shared.features.onboarding.api.di.OnboardingDataModule
import com.velkonost.getbetter.shared.features.onboarding.presentation.di.OnboardingPresentationModule
import com.velkonost.getbetter.shared.features.presentation.di.WisdomPresentationModule
import com.velkonost.getbetter.shared.features.profile.data.di.ProfileDataModule
import com.velkonost.getbetter.shared.features.profile.di.ProfilePresentationModule
import com.velkonost.getbetter.shared.features.profiledetail.presentation.di.ProfileDetailPresentationModule
import com.velkonost.getbetter.shared.features.settings.presentation.di.SettingsPresentationModule
import com.velkonost.getbetter.shared.features.social.data.di.SocialDataModule
import com.velkonost.getbetter.shared.features.social.di.SocialPresentationModule
import com.velkonost.getbetter.shared.features.splash.data.di.SplashDataModule
import com.velkonost.getbetter.shared.features.splash.presentation.di.SplashPresentationModule
import com.velkonost.getbetter.shared.features.taskdetail.data.di.TaskDetailDataModule
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
            AddAreaDataModule,
            NotesDataModule,
            TaskDetailDataModule,
            NoteDetailDataModule,
            AreaDetailDataModule,
            CreateNoteDataModule,
            TasksDataModule,
            UserInfoDataModule,
            LikesDataModule,
            CommentsDataModule,
            FollowsDataModule,
            CalendarsDataModule,
            FeedbackDataModule,
            AbilitiesDataModule,
            AffirmationsDataModule,

            AuthDataModule,
            AuthDomainModule,
            AuthPresentationModule,

            SplashDataModule,
            SplashPresentationModule,

            SocialDataModule,
            SocialPresentationModule,

            OnboardingDataModule,
            OnboardingPresentationModule,

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
            AbilitiesPresentationModule,
            AbilityDetailsPresentationModule,

            ProfileDataModule,
            ProfilePresentationModule,
        )

        modules?.let(::modules)
    }
}