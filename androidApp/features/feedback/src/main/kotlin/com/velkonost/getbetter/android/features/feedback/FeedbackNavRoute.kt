package com.velkonost.getbetter.android.features.feedback

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.feedback.presentation.FeedbackViewModel
import org.koin.androidx.compose.koinViewModel

object FeedbackNavRoute : NavRoute<FeedbackViewModel> {

    override val route: String
        get() = NavigationScreen.FeedbackNavScreen.route

    @Composable
    override fun Content(viewModel: FeedbackViewModel) = FeedbackScreen(viewModel = viewModel)

    override val viewModel: FeedbackViewModel
        @Composable get() = koinViewModel()

}