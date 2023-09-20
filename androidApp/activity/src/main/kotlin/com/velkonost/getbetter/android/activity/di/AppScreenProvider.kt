package com.velkonost.getbetter.android.activity.di

import com.velkonost.getbetter.android.features.auth.AuthNavRoute
import com.velkonost.getbetter.android.features.detail.DetailNavRoute
import com.velkonost.getbetter.android.features.home.HomeNavRoute
import com.velkonost.getbetter.android.features.home.WisdomNavRoute
import com.velkonost.getbetter.android.features.profile.ProfileNavRoute

val AppScreens = setOf(
    AuthNavRoute,
    HomeNavRoute,
    ProfileNavRoute,
    WisdomNavRoute,
    DetailNavRoute
)

val NavigationScreens = setOf(
    HomeNavRoute,
    DetailNavRoute,
    WisdomNavRoute,
    ProfileNavRoute
)