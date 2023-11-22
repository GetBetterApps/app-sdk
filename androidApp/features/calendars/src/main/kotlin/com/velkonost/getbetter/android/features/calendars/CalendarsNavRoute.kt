package com.velkonost.getbetter.android.features.calendars

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.calendars.presentation.CalendarsViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object CalendarsNavRoute : NavRoute<CalendarsViewModel> {

    override val route: String
        get() = NavigationScreen.CalendarsNavScreen.route

    override val menuIcon: ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: CalendarsViewModel) = CalendarsScreen(viewModel = viewModel)

    @Composable
    override fun Content(
        viewModel: CalendarsViewModel,
        forceHideBottomBar: MutableState<Boolean>
    ) {
        viewModel.init()
        CalendarsScreen(
            viewModel = viewModel,
            forceHideBottomBar = forceHideBottomBar
        )
    }


    override val viewModel: CalendarsViewModel
        @Composable get() = koinViewModel()

}