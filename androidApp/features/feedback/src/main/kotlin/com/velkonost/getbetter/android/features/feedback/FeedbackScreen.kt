package com.velkonost.getbetter.android.features.feedback

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.feedback.components.FeedbackItem
import com.velkonost.getbetter.android.features.feedback.components.FeedbackListHeader
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.shared.features.feedback.presentation.FeedbackViewModel

@Composable
fun FeedbackScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedbackViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading && state.items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyRow {
                item {
                    FeedbackListHeader {

                    }
                }

                items(state.items, key = { it.id!! }) { item ->
                    FeedbackItem(
                        item = item,
                        onClick = {

                        }
                    )
                }
            }
        }

    }
}