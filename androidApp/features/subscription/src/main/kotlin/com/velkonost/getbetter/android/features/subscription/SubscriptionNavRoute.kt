package com.velkonost.getbetter.android.features.subscription

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.subscription.presentation.SubscriptionViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import org.koin.androidx.compose.koinViewModel

object SubscriptionNavRoute : NavRoute<SubscriptionViewModel> {

    override val route: String
        get() = NavigationScreen.SubscriptionNavScreen.route

    @Composable
    override fun Content(viewModel: SubscriptionViewModel) = SubscriptionScreen(viewModel = viewModel)

    override val viewModel: SubscriptionViewModel
        @Composable get() = koinViewModel()

}