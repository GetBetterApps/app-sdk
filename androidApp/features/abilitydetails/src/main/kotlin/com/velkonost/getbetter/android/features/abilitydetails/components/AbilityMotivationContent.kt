package com.velkonost.getbetter.android.features.abilitydetails.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.core.model.task.Affirmation
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

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

        Column {
            Spacer(modifier.weight(1f))
            Row(modifier = modifier.padding(bottom = 64.dp)) {
                Spacer(modifier.weight(1f))
                Image(
                    modifier = modifier
                        .padding(end = 12.dp)
                        .size(42.dp)
                        .background(
                            color = colorResource(resource = SharedR.colors.background_icon)
                                .copy(alpha = 0.6f),
                            shape = androidx.compose.material3.MaterialTheme.shapes.medium
                        )
                        .padding(8.dp),
                    painter = painterResource(imageResource = SharedR.images.ic_empty_star),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_active))
                )

                Image(
                    modifier = modifier
                        .padding(end = 24.dp)
                        .size(42.dp)
                        .background(
                            color = colorResource(resource = SharedR.colors.background_icon)
                                .copy(alpha = 0.6f),
                            shape = androidx.compose.material3.MaterialTheme.shapes.medium
                        )
                        .padding(8.dp),
                    painter = painterResource(imageResource = SharedR.images.ic_share),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_active))
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