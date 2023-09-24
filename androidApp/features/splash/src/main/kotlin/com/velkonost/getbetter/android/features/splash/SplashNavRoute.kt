package com.velkonost.getbetter.android.features.splash

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.splash.presentation.SplashViewModel
import org.koin.androidx.compose.koinViewModel

object SplashNavRoute : NavRoute<SplashViewModel> {

    override val route: String
        get() = NavigationScreen.SplashNavScreen.route

    @Composable
    override fun Content(viewModel: SplashViewModel) = SplashScreen(viewModel = viewModel)

    override val viewModel: SplashViewModel
        @Composable get() = koinViewModel()

}
