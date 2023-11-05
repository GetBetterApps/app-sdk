package com.velkonost.getbetter.android.features.social

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.social.SocialViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object SocialNavRoute : NavRoute<SocialViewModel> {

    override val route: String
        get() = NavigationScreen.SocialNavScreen.route

    override val menuIcon: ImageResource = SharedR.images.ic_menu_profile

    @Composable
    override fun Content(viewModel: SocialViewModel) = SocialScreen(viewModel = viewModel)

    override val viewModel: SocialViewModel
        @Composable get() = koinViewModel()

}