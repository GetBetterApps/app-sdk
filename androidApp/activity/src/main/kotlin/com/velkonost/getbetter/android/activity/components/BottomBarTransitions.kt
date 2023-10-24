package com.velkonost.getbetter.android.activity.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.bottomBarEnter(): EnterTransition =
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(
            durationMillis = 290,
            delayMillis = 10,
            easing = FastOutSlowInEasing
        ),
        initialOffset = { it / 4 }
    ).plus(
        fadeIn(
            animationSpec = tween(
                durationMillis = 150,
                delayMillis = 10,
                easing = FastOutSlowInEasing
            )
        )
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.bottomBarExit(): ExitTransition =
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(
            durationMillis = 280,
            delayMillis = 20,
            easing = FastOutSlowInEasing
        ),
        targetOffset = { it / 4 }
    ).plus(
        fadeOut(
            animationSpec = tween(
                durationMillis = 280,
                delayMillis = 20,
                easing = FastOutSlowInEasing
            )
        )
    )