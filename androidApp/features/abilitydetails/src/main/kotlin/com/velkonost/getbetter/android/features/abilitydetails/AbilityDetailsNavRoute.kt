package com.velkonost.getbetter.android.features.abilitydetails

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_ABILITY
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.AbilityDetailsViewModel
import org.koin.androidx.compose.koinViewModel

object AbilityDetailsNavRoute : NavRoute<AbilityDetailsViewModel> {

    override val route: String
        get() = NavigationScreen.AbilityDetailsNavScreen.route

    @Composable
    override fun Content(viewModel: AbilityDetailsViewModel) =
        AbilityDetailsScreen(viewModel = viewModel)

    override val viewModel: AbilityDetailsViewModel
        @Composable get() = koinViewModel()

    override fun getArguments(): List<NamedNavArgument> =
        listOf(navArgument(ARG_ABILITY) { type = NavType.StringType })
}