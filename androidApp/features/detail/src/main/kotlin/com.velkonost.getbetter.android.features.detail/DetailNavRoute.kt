package com.velkonost.getbetter.android.features.detail

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.detail.presentation.DetailViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object DetailNavRoute : NavRoute<DetailViewModel> {

    override val route: String
        get() = NavigationScreen.DetailNavScreen.route

    override val menuIcon: ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: DetailViewModel) = DetailScreen(viewModel = viewModel)

    override val viewModel: DetailViewModel
        @Composable get() = koinViewModel()
}