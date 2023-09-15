package com.velkonost.getbetter.shared.core.vm.navigation

sealed class NavigationScreen(val route: String) {
    data object SplashNavScreen : NavigationScreen(SPLASH_DESTINATION)

    data object AuthNavScreen : NavigationScreen(AUTH_DESTINATION)

    data object HomeNavScreen : NavigationScreen(HOME_DESTINATION)

    data object DetailNavScreen : NavigationScreen(DETAIL_DESTINATION)
}

const val NAV_PREFIX: String = "com.velkonost.getbetter"

const val SPLASH_DESTINATION: String = "$NAV_PREFIX.splash/SplashScreen"
const val AUTH_DESTINATION: String = "$NAV_PREFIX.auth/AuthScreen"
const val HOME_DESTINATION: String = "$NAV_PREFIX.home/HomeScreen"
const val DETAIL_DESTINATION: String = "$NAV_PREFIX.detail/DetailScreen"