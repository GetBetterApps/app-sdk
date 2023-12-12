package com.velkonost.getbetter.android.features.abilitydetails.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.thumbnail.ThumbnailPlugin
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin
import com.velkonost.getbetter.shared.core.model.task.Affirmation
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AbilityMotivationContent(
    modifier: Modifier = Modifier,
    items: List<Affirmation>,
    isActive: Boolean
) {

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val snappingLayout = remember(listState) { SnapLayoutInfoProvider(listState) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            flingBehavior = rememberFlingBehaviorMultiplier(
                multiplier = 0.2f,
                baseFlingBehavior = flingBehavior
            ),
        ) {
            items(items, key = { it.id }) { item ->
                val isItemAppeared = remember {
                    mutableStateOf(false)
                }
                val imageScale by animateFloatAsState(
                    targetValue = if (!isItemAppeared.value) 1F else 1.1F,
                    animationSpec = tween(durationMillis = 750, easing = FastOutLinearInEasing),
                    label = ""
                )
                val imageAlpha by animateFloatAsState(
                    targetValue = if (!isItemAppeared.value) 0F else 1F,
                    animationSpec = tween(durationMillis = 750, easing = FastOutLinearInEasing),
                    label = ""
                )

                val textAlpha by animateFloatAsState(
                    targetValue = if (!isItemAppeared.value) 0F else 1F,
                    animationSpec = tween(durationMillis = 150, easing = FastOutLinearInEasing),
                    label = ""
                )

                val isItemWithKeyInView by remember {
                    derivedStateOf {
                        !listState.isScrollInProgress &&
                                listState.layoutInfo
                                    .visibleItemsInfo
                                    .any { it.key == item.id }
                    }
                }
                if (isItemWithKeyInView) {
                    LaunchedEffect(Unit) {
                        delay(500)
                        isItemAppeared.value = true
                    }
                } else {
                    LaunchedEffect(Unit) {
                        isItemAppeared.value = false
                    }
                }
                Box {
                    CoilImage(
                        modifier = modifier
                            .fillParentMaxSize()
                            .align(Alignment.Center),
                        imageRequest = {
                            ImageRequest
                                .Builder(context)
                                .data(item.imageUrl)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .crossfade(true)
                                .build()
                        },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                        ),
                        component = rememberImageComponent {
                            +ThumbnailPlugin()
                            +BlurTransformationPlugin(radius = 20)
                        }
                    )
                    SubcomposeAsyncImage(
                        modifier = modifier
                            .fillParentMaxSize()
                            .alpha(imageAlpha)
                            .scale(if (isActive) imageScale else 1f),
                        model = ImageRequest
                            .Builder(context)
                            .data(item.imageUrl)
                            .diskCachePolicy(CachePolicy.ENABLED)
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                    )

                    AffirmationText(
                        modifier = modifier.alpha(textAlpha),
                        text = item.text
                    )
                }
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