package com.velkonost.getbetter.android.features.settings

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
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
}