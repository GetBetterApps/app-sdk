package com.velkonost.getbetter.android.activity.di

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.velkonost.getbetter.android.features.abilities.AbilitiesNavRoute
import com.velkonost.getbetter.android.features.abilitydetails.AbilityDetailsNavRoute
import com.velkonost.getbetter.android.features.addarea.AddAreaNavRoute
import com.velkonost.getbetter.android.features.auth.AuthNavRoute
import com.velkonost.getbetter.android.features.calendars.CalendarsNavRoute
import com.velkonost.getbetter.android.features.diary.DiaryNavRoute
import com.velkonost.getbetter.android.features.feedback.FeedbackNavRoute
import com.velkonost.getbetter.android.features.home.WisdomNavRoute
import com.velkonost.getbetter.android.features.notedetail.NoteDetailNavRoute
import com.velkonost.getbetter.android.features.profile.ProfileNavRoute
import com.velkonost.getbetter.android.features.settings.SettingsNavRoute
import com.velkonost.getbetter.android.features.social.SocialNavRoute
import com.velkonost.getbetter.android.features.splash.SplashNavRoute
import com.velkonost.getbetter.android.features.taskdetail.TaskDetailNavRoute
import com.velkonost.getbetter.core.compose.provide
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen

val AppScreens = setOf(
    AuthNavRoute,
    AddAreaNavRoute,
    NoteDetailNavRoute,
    TaskDetailNavRoute,
    AbilityDetailsNavRoute
)

val NavigationScreens = setOf(
    SocialNavRoute,
    DiaryNavRoute,
    CalendarsNavRoute,
    AbilitiesNavRoute,
    ProfileNavRoute
)

fun NavGraphBuilder.addSplashRoute(
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) {
    navigation(
        route = "${NavigationScreen.SplashNavScreen.route}_root",
        startDestination = NavigationScreen.SplashNavScreen.route
    ) {
        SplashNavRoute.provide(this, navController, forceHideBottomBar)
    }
}


fun NavGraphBuilder.addSocialRoute(
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) {
    navigation(
        route = "${NavigationScreen.SocialNavScreen.route}_root",
        startDestination = NavigationScreen.SocialNavScreen.route
    ) {
        SocialNavRoute.provide(this, navController, forceHideBottomBar)
    }
}

fun NavGraphBuilder.addDiaryRoute(
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) {
    navigation(
        route = "${NavigationScreen.DiaryNavScreen.route}_root",
        startDestination = NavigationScreen.DiaryNavScreen.route
    ) {
        DiaryNavRoute.provide(this, navController, forceHideBottomBar)
    }
}

fun NavGraphBuilder.addCalendarRoute(
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) {
    navigation(
        route = "${NavigationScreen.CalendarsNavScreen.route}_root",
        startDestination = NavigationScreen.CalendarsNavScreen.route
    ) {
        CalendarsNavRoute.provide(this, navController, forceHideBottomBar)
    }
}

fun NavGraphBuilder.addWisdomRoute(
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) {
    navigation(
        route = "${NavigationScreen.WisdomNavScreen.route}_root",
        startDestination = NavigationScreen.WisdomNavScreen.route
    ) {
        WisdomNavRoute.provide(this, navController, forceHideBottomBar)
    }
}

fun NavGraphBuilder.addAbilitiesRoute(
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) {
    navigation(
        route = "${NavigationScreen.AbilitiesNavScreen.route}_root",
        startDestination = NavigationScreen.AbilitiesNavScreen.route
    ) {
        AbilitiesNavRoute.provide(this, navController, forceHideBottomBar)
    }
}

fun NavGraphBuilder.addProfileRoute(
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) {
    navigation(
        route = "${NavigationScreen.ProfileNavScreen.route}_root",
        startDestination = NavigationScreen.ProfileNavScreen.route
    ) {
        ProfileNavRoute.provide(this, navController, forceHideBottomBar)
        FeedbackNavRoute.provide(this, navController, forceHideBottomBar)
        SettingsNavRoute.provide(this, navController, forceHideBottomBar)
    }
}
