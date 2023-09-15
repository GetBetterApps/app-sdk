package com.velkonost.getbetter.android.features.auth

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.AnimatedBackStack
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.auth.presentation.AuthViewModel
import org.koin.androidx.compose.koinViewModel

object AuthNavRoute : NavRoute<AuthViewModel> {

    override val route: String
        get() = NavigationScreen.AuthNavScreen.route

    @Composable
    override fun Content(viewModel: AuthViewModel) = AuthScreen(viewModel = viewModel)

    override val viewModel: AuthViewModel
        @Composable get() = koinViewModel()

    override fun getEnterTransition() = AuthEnterTransition

    override fun getExitTransition() = AuthExitTransition

}

private val AuthEnterTransition: AnimatedBackStack.() -> EnterTransition? = {
    slideIntoContainer(
        towards = androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(
            durationMillis = 290,
            delayMillis = 10,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        ),
        initialOffset = { it / 4 }
    ).plus(
        fadeIn(
            animationSpec = tween(
                durationMillis = 150,
                delayMillis = 10,
                easing = androidx.compose.animation.core.FastOutSlowInEasing
            )
        )
    )
}

private val AuthExitTransition: AnimatedBackStack.() -> ExitTransition? = {
    slideOutOfContainer(
        towards = androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(
            durationMillis = 280,
            delayMillis = 20,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        ),
        targetOffset = { it / 4 }
    ).plus(
        fadeOut(
            animationSpec = tween(
                durationMillis = 280,
                delayMillis = 20,
                easing = androidx.compose.animation.core.FastOutSlowInEasing
            )
        )
    )
}