package com.velkonost.getbetter.android.features.addarea

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.addarea.presentation.AddAreaViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object AddAreaNavRoute : NavRoute<AddAreaViewModel> {

    override val route: String
        get() = NavigationScreen.AddAreaNavScreen.route

    override fun menuIcon(): ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: AddAreaViewModel) = AddAreaScreen(viewModel = viewModel)

    override val viewModel: AddAreaViewModel
        @Composable get() = koinViewModel()

}