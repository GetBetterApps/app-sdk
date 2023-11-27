package com.velkonost.getbetter.android.features.settings

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_USER_ID
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.settings.presentation.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

object SettingsNavRoute : NavRoute<SettingsViewModel> {

    override val route: String
        get() = NavigationScreen.SettingsNavScreen.route

    @Composable
    override fun Content(viewModel: SettingsViewModel) = SettingsScreen(viewModel = viewModel)

    override val viewModel: SettingsViewModel
        @Composable get() = koinViewModel()

    override fun getArguments(): List<NamedNavArgument> =
        listOf(navArgument(ARG_USER_ID) { type = NavType.StringType })
}