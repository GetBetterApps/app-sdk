package com.velkonost.getbetter.android.activity.di

import com.velkonost.getbetter.android.features.auth.AuthNavRoute
import com.velkonost.getbetter.android.features.calendars.CalendarsNavRoute
import com.velkonost.getbetter.android.features.detail.DetailNavRoute
import com.velkonost.getbetter.android.features.diary.DiaryNavRoute
import com.velkonost.getbetter.android.features.home.HomeNavRoute
import com.velkonost.getbetter.android.features.home.WisdomNavRoute
import com.velkonost.getbetter.android.features.profile.ProfileNavRoute
import com.velkonost.getbetter.android.features.social.SocialNavRoute
import com.velkonost.getbetter.android.features.splash.SplashNavRoute

val AppScreens = setOf(
    SplashNavRoute,
    AuthNavRoute,

    SocialNavRoute,
    DiaryNavRoute,
    CalendarsNavRoute,
    ProfileNavRoute,
    WisdomNavRoute,

    HomeNavRoute,
    DetailNavRoute
)

val NavigationScreens = setOf(
    SocialNavRoute,
    DiaryNavRoute,
    CalendarsNavRoute,
    WisdomNavRoute,
    ProfileNavRoute
)