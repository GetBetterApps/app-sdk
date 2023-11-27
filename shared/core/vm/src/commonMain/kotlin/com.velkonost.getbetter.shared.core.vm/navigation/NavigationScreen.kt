package com.velkonost.getbetter.shared.core.vm.navigation

sealed class NavigationScreen(val route: String) {
    data object SplashNavScreen : NavigationScreen(SPLASH_DESTINATION)

    data object AuthNavScreen : NavigationScreen(AUTH_DESTINATION)

    data object SocialNavScreen : NavigationScreen(SOCIAL_DESTINATION)
    data object DiaryNavScreen : NavigationScreen(DIARY_DESTINATION)
    data object AddAreaNavScreen : NavigationScreen(ADD_AREA_DESTINATION)
    data object AreaDetailNavScreen : NavigationScreen(AREA_DETAIL_DESTINATION)
    data object NoteDetailNavScreen : NavigationScreen(
        "$NOTE_DETAIL_DESTINATION/?$ARG_NOTE={$ARG_NOTE}"
    )

    data object CalendarsNavScreen : NavigationScreen(CALENDARS_DESTINATION)
    data object WisdomNavScreen : NavigationScreen(WISDOM_DESTINATION)

    data object ProfileNavScreen : NavigationScreen(PROFILE_DESTINATION)

    data object FeedbackNavScreen : NavigationScreen(FEEDBACK_DESTINATION)
}

const val NAV_PREFIX: String = "com.velkonost.getbetter"

const val SPLASH_DESTINATION: String = "$NAV_PREFIX.splash/SplashScreen"
const val AUTH_DESTINATION: String = "$NAV_PREFIX.auth/AuthScreen"

const val SOCIAL_DESTINATION: String = "$NAV_PREFIX.social/SocialScreen"

const val DIARY_DESTINATION: String = "$NAV_PREFIX.diary/DiaryScreen"
const val ADD_AREA_DESTINATION: String = "$NAV_PREFIX.diary/AddAreaScreen"
const val AREA_DETAIL_DESTINATION: String = "$NAV_PREFIX.diary/AreaDetailScreen"
const val NOTE_DETAIL_DESTINATION: String = "$NAV_PREFIX.diary/NoteDetailScreen"

const val CALENDARS_DESTINATION: String = "$NAV_PREFIX.calendars/CalendarsScreen"
const val WISDOM_DESTINATION: String = "$NAV_PREFIX.wisdom/WisdomScreen"

const val PROFILE_DESTINATION: String = "$NAV_PREFIX.profile/ProfileScreen"
const val FEEDBACK_DESTINATION: String = "$NAV_PREFIX.profile/FeedbackScreen"

const val ARG_NOTE: String = "arg_note"
const val ARG_IDENTIFY_ANONYMOUS: String = "arg_identify_anonymous"