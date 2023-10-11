package com.velkonost.getbetter.android.features.home

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.home.presentation.HomeViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object HomeNavRoute : NavRoute<HomeViewModel> {

    override val route: String
        get() = NavigationScreen.HomeNavScreen.route

    override val menuIcon: ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: HomeViewModel) = HomeScreen(viewModel = viewModel)

    override val viewModel: HomeViewModel
        @Composable get() = koinViewModel()

}