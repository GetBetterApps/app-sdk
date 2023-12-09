package com.velkonost.getbetter.android.features.abilities

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.abilities.presentation.AbilitiesViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object AbilitiesNavRoute : NavRoute<AbilitiesViewModel> {

    override val route: String
        get() = NavigationScreen.AbilitiesNavScreen.route

    override val menuIcon: ImageResource = SharedR.images.ic_menu_wisdom

    @Composable
    override fun Content(viewModel: AbilitiesViewModel) = AbilitiesScreen(viewModel = viewModel)

    override val viewModel: AbilitiesViewModel
        @Composable get() = koinViewModel()

}