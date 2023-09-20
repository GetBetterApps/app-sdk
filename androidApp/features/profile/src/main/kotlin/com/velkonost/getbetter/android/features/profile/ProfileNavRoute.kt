package com.velkonost.getbetter.android.features.profile

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.Crossfade
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
import com.velkonost.getbetter.shared.features.profile.ProfileViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object ProfileNavRoute: NavRoute<ProfileViewModel> {

    override val route: String
        get() = NavigationScreen.ProfileNavScreen.route

    override fun menuIcon(): ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: ProfileViewModel) = ProfileScreen()//viewModel = viewModel

    override val viewModel: ProfileViewModel
        @Composable get() = koinViewModel()

    override fun getEnterTransition(): (AnimatedBackStack.() -> EnterTransition?) = HomePopEnterTransition

    override fun getExitTransition(): (AnimatedBackStack.() -> ExitTransition?) = HomePopExitTransition

    override fun getPopEnterTransition() = HomePopEnterTransition

    override fun getPopExitTransition() = HomePopExitTransition
}

private val HomePopEnterTransition: AnimatedBackStack.() -> EnterTransition? = {
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
}

private val HomePopExitTransition: AnimatedBackStack.() -> ExitTransition? = {
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
}