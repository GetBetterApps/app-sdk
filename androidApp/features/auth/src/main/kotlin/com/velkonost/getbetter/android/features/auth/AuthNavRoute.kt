package com.velkonost.getbetter.android.features.auth

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.AnimatedBackStack
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.core.compose.composable.GeneralEnterTransition
import com.velkonost.getbetter.core.compose.composable.GeneralExitTransition
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

    override fun getEnterTransition(): (AnimatedBackStack.() -> EnterTransition?) = GeneralEnterTransition

    override fun getExitTransition(): (AnimatedBackStack.() -> ExitTransition?) = GeneralExitTransition

    override fun getPopEnterTransition() = GeneralEnterTransition

    override fun getPopExitTransition() = GeneralExitTransition

}
