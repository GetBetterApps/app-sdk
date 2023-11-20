package com.velkonost.getbetter.android.features.diary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.calendars.DiaryViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object DiaryNavRoute : NavRoute<DiaryViewModel> {

    override val route: String
        get() = NavigationScreen.DiaryNavScreen.route

    override val menuIcon: ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: DiaryViewModel) {
        DiaryScreen(viewModel = viewModel)
    }

    @Composable
    override fun Content(
        viewModel: DiaryViewModel,
        forceHideBottomBar: MutableState<Boolean>
    ) {
        viewModel.init()
        DiaryScreen(
            viewModel = viewModel,
            forceHideBottomBar = forceHideBottomBar
        )
    }

    override val viewModel: DiaryViewModel
        @Composable get() = koinViewModel()

}