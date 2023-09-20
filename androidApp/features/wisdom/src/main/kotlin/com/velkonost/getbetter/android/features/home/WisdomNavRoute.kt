package com.velkonost.getbetter.android.features.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.AnimatedBackStack
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.core.compose.composable.GeneralEnterTransition
import com.velkonost.getbetter.core.compose.composable.GeneralExitTransition
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.presentation.WisdomViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object WisdomNavRoute : NavRoute<WisdomViewModel> {

    override val route: String
        get() = NavigationScreen.WisdomNavScreen.route

    override fun menuIcon(): ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: WisdomViewModel) = WisdomScreen(viewModel = viewModel)

    override val viewModel: WisdomViewModel
        @Composable get() = koinViewModel()

    override fun getEnterTransition(): (AnimatedBackStack.() -> EnterTransition?) = GeneralEnterTransition

    override fun getExitTransition(): (AnimatedBackStack.() -> ExitTransition?) = GeneralExitTransition

    override fun getPopEnterTransition() = GeneralEnterTransition

    override fun getPopExitTransition() = GeneralExitTransition
}