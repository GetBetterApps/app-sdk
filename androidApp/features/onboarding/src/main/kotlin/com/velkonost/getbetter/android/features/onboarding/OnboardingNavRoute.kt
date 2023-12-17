package com.velkonost.getbetter.android.features.onboarding

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.onboarding.presentation.OnboardingViewModel
import org.koin.androidx.compose.koinViewModel

object OnboardingNavRoute : NavRoute<OnboardingViewModel> {

    override val route: String
        get() = NavigationScreen.OnboardingNavScreen.route

    @Composable
    override fun Content(viewModel: OnboardingViewModel) =
        OnboardingScreen(viewModel = viewModel)

    override val viewModel: OnboardingViewModel
        @Composable get() = koinViewModel()

}