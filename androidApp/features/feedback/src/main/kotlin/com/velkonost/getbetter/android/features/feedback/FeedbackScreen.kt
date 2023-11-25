package com.velkonost.getbetter.android.features.feedback

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.shared.features.feedback.presentation.FeedbackViewModel

@Composable
fun FeedbackScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedbackViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    LazyRow {
        items(state.items, key = { it.id!! }) { item ->

        }
    }

}