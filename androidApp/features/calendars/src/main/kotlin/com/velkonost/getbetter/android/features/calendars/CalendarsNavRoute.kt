package com.velkonost.getbetter.android.features.calendars

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.diary.CalendarsViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object CalendarsNavRoute : NavRoute<CalendarsViewModel> {

    override val route: String
        get() = NavigationScreen.CalendarsNavScreen.route

    override fun menuIcon(): ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: CalendarsViewModel) = CalendarsScreen()

    override val viewModel: CalendarsViewModel
        @Composable get() = koinViewModel()

}