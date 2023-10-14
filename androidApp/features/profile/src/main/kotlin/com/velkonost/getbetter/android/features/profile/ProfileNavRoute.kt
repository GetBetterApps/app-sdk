package com.velkonost.getbetter.android.features.profile

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.profile.ProfileViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object ProfileNavRoute : NavRoute<ProfileViewModel> {

    override val route: String
        get() = NavigationScreen.ProfileNavScreen.route

    override val menuIcon: ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: ProfileViewModel) = ProfileScreen(viewModel = viewModel)

    override val viewModel: ProfileViewModel
        @Composable get() = koinViewModel()
}
