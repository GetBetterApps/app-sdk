package com.velkonost.getbetter.android.features.calendars.extension

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow

suspend fun LazyListState.centerItem(index: Int) {
    suspend fun locateTarget(): Boolean {
        val layoutInfo = layoutInfo
        val containerSize =
            layoutInfo.viewportSize.width - layoutInfo.beforeContentPadding - layoutInfo.afterContentPadding

        val target = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
            ?: return false
        val targetOffset = containerSize / 2f - target.size / 2f
        animateScrollBy(target.offset - targetOffset)
        return true
    }

    if (!locateTarget()) {
        val visibleItemsInfo = layoutInfo.visibleItemsInfo
        val currentIndex = visibleItemsInfo.getOrNull(visibleItemsInfo.size / 2)?.index ?: -1
        scrollToItem(
            if (index > currentIndex) {
                (index - visibleItemsInfo.size + 1)
            } else {
                index
            }.coerceIn(0, layoutInfo.totalItemsCount)
        )
        locateTarget()
    }
}

@Composable
fun LazyListState.OnSideReached(
    buffer: Int = 5,
    isLoadingRight: Boolean,
    onLoadMoreRight: () -> Unit,
) {
    val shouldLoadMoreRight = remember {
        derivedStateOf {
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMoreRight, isLoadingRight) {
        snapshotFlow { shouldLoadMoreRight.value && !isLoadingRight }.collect {
            if (it) onLoadMoreRight()
        }
    }
}