package com.velkonost.getbetter.android.features.areadetail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.areadetail.presentation.AreaDetailViewModel
import org.koin.androidx.compose.koinViewModel

object AreaDetailNavRoute : NavRoute<AreaDetailViewModel> {

    override val route: String
        get() = NavigationScreen.AreaDetailNavScreen.route

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content(viewModel: AreaDetailViewModel) = AreaDetailScreen(viewModel = viewModel)

    override val viewModel: AreaDetailViewModel
        @Composable get() = koinViewModel()

}