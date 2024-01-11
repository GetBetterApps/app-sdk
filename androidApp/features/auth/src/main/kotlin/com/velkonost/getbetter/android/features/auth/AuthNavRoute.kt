package com.velkonost.getbetter.android.features.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_IDENTIFY_ANONYMOUS
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.auth.presentation.AuthViewModel
import org.koin.androidx.compose.koinViewModel

object AuthNavRoute : NavRoute<AuthViewModel> {

    override val route: String
        get() = NavigationScreen.AuthNavScreen.route

    @Composable
    override fun Content(viewModel: AuthViewModel) = AuthScreen(viewModel = viewModel)

    @Composable
    override fun Content(
        viewModel: AuthViewModel,
        forceHideBottomBar: MutableState<Boolean>
    ) {
        AuthScreen(
            viewModel = viewModel,
            forceHideBottomBar = forceHideBottomBar
        )
    }

    override val viewModel: AuthViewModel
        @Composable get() = koinViewModel()

    override fun getArguments(): List<NamedNavArgument> =
        listOf(navArgument(ARG_IDENTIFY_ANONYMOUS) { type = NavType.StringType })
}
