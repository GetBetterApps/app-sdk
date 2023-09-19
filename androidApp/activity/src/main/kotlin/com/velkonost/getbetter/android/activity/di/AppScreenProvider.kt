package com.velkonost.getbetter.android.activity.di

import com.velkonost.getbetter.android.features.auth.AuthNavRoute
import com.velkonost.getbetter.android.features.detail.DetailNavRoute
import com.velkonost.getbetter.android.features.home.HomeNavRoute

val AppScreens = setOf(
    AuthNavRoute,
    HomeNavRoute,
    DetailNavRoute
)

val NavigationScreens = setOf(
    HomeNavRoute,
    DetailNavRoute
)