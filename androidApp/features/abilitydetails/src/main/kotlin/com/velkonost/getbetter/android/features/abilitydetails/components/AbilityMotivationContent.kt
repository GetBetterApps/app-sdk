package com.velkonost.getbetter.android.features.abilitydetails.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.shared.core.model.task.Affirmation

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AbilityMotivationContent(
    modifier: Modifier = Modifier,
    items: List<Affirmation>,
    isActive: Boolean
) {

    val listState = rememberLazyListState()
    val snappingLayout = remember(listState) { SnapLayoutInfoProvider(listState) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            flingBehavior = rememberFlingBehaviorMultiplier(
                multiplier = 0.4f,
                baseFlingBehavior = flingBehavior
            ),
        ) {
            items(items, key = { it.id }) { item ->
                AbilityMotivationItem(
                    listState = listState,
                    isActive = isActive,
                    item = item
                )
            }
        }
    }

}

private class FlingBehaviourMultiplier(
    private val multiplier: Float,
    private val baseFlingBehavior: FlingBehavior
) : FlingBehavior {
    override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
        return with(baseFlingBehavior) {
            performFling(initialVelocity * multiplier)
        }
    }
}

@Composable
fun rememberFlingBehaviorMultiplier(
    multiplier: Float,
    baseFlingBehavior: FlingBehavior
): FlingBehavior = remember(multiplier, baseFlingBehavior) {
    FlingBehaviourMultiplier(multiplier, baseFlingBehavior)
}