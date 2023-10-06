package com.velkonost.getbetter.core.compose.extensions

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow

@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 0,
    isLoading: Boolean,
    onLoadMore: () -> Unit
) {

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore, isLoading) {
        snapshotFlow { shouldLoadMore.value && !isLoading }
            .collect {
                if (it) onLoadMore()
            }
    }

}