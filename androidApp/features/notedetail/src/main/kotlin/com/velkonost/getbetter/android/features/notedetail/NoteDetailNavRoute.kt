package com.velkonost.getbetter.android.features.notedetail

import androidx.compose.runtime.Composable
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.features.notedetail.presentation.NoteDetailViewModel
import org.koin.androidx.compose.koinViewModel

object NoteDetailNavRoute : NavRoute<NoteDetailViewModel> {
    override val route: String
        get() = NavigationScreen.NoteDetailNavScreen.route

    @Composable
    override fun Content(viewModel: NoteDetailViewModel) = NoteDetailScreen()

    override val viewModel: NoteDetailViewModel
        @Composable get() = koinViewModel()
}