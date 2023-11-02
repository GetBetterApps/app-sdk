package com.velkonost.getbetter.android.features.notedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.notedetail.components.NoteDetailHeader
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.shared.features.notedetail.presentation.NoteDetailViewModel
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NavigateBack

@Composable
fun NoteDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {

            NoteDetailHeader {
                viewModel.dispatch(NavigateBack)
            }

        }
    }

}