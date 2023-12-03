package com.velkonost.getbetter.android.features.taskdetail

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_TASK
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.taskdetail.presentation.TaskDetailViewModel
import org.koin.androidx.compose.koinViewModel

object TaskDetailNavRoute : NavRoute<TaskDetailViewModel> {

    override val route: String
        get() = NavigationScreen.TaskDetailNavScreen.route

    @Composable
    override fun Content(viewModel: TaskDetailViewModel) = TaskDetailScreen(viewModel = viewModel)

    override val viewModel: TaskDetailViewModel
        @Composable get() = koinViewModel()

    override fun getArguments(): List<NamedNavArgument> =
        listOf(navArgument(ARG_TASK) { type = NavType.StringType })
}
