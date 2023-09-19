package com.velkonost.getbetter.android.features.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.AnimatedBackStack
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.home.presentation.HomeViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object HomeNavRoute : NavRoute<HomeViewModel> {

    override val route: String
        get() = NavigationScreen.HomeNavScreen.route

    override fun menuIcon(): ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: HomeViewModel) = HomeScreen(viewModel = viewModel)

    override val viewModel: HomeViewModel
        @Composable get() = koinViewModel()

    override fun getPopEnterTransition() = HomePopEnterTransition

    override fun getPopExitTransition() = HomePopExitTransition
}

private val HomePopEnterTransition: AnimatedBackStack.() -> EnterTransition? = {
    fadeIn(animationSpec = tween(durationMillis = 300))
}

private val HomePopExitTransition: AnimatedBackStack.() -> ExitTransition? = {
    fadeOut(
        animationSpec = tween(durationMillis = 300),
        targetAlpha = 1f
    )
}