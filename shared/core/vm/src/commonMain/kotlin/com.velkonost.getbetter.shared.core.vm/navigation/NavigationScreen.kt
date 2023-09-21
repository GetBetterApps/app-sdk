package com.velkonost.getbetter.shared.core.vm.navigation

sealed class NavigationScreen(val route: String) {
    data object SplashNavScreen : NavigationScreen(SPLASH_DESTINATION)

    data object AuthNavScreen : NavigationScreen(AUTH_DESTINATION)

    data object SocialNavScreen : NavigationScreen(SOCIAL_DESTINATION)
    data object DiaryNavScreen : NavigationScreen(DIARY_DESTINATION)
    data object CalendarsNavScreen : NavigationScreen(CALENDARS_DESTINATION)
    data object WisdomNavScreen: NavigationScreen(WISDOM_DESTINATION)
    data object ProfileNavScreen: NavigationScreen(PROFILE_DESTINATION)

    data object HomeNavScreen : NavigationScreen(HOME_DESTINATION)

    data object DetailNavScreen : NavigationScreen(DETAIL_DESTINATION)
}

const val NAV_PREFIX: String = "com.velkonost.getbetter"

const val SPLASH_DESTINATION: String = "$NAV_PREFIX.splash/SplashScreen"
const val AUTH_DESTINATION: String = "$NAV_PREFIX.auth/AuthScreen"

const val SOCIAL_DESTINATION: String = "$NAV_PREFIX.social/SocialScreen"
const val DIARY_DESTINATION: String = "$NAV_PREFIX.diary/DiaryScreen"
const val CALENDARS_DESTINATION: String = "$NAV_PREFIX.calendars/CalendarsScreen"
const val WISDOM_DESTINATION: String = "$NAV_PREFIX.wisdom/WisdomScreen"
const val PROFILE_DESTINATION: String = "$NAV_PREFIX.profile/ProfileScreen"

const val HOME_DESTINATION: String = "$NAV_PREFIX.home/HomeScreen"
const val DETAIL_DESTINATION: String = "$NAV_PREFIX.detail/DetailScreen"