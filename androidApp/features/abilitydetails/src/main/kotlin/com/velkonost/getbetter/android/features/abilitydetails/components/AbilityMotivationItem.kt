package com.velkonost.getbetter.android.features.abilitydetails.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun LazyItemScope.AbilityMotivationItem(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    isActive: Boolean,
    item: Affirmation
) {
    val context = LocalContext.current

    val isItemAppeared = remember { mutableStateOf(false) }
    val isTextVisible = remember { mutableStateOf(false) }

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
        targetValue = if (!isTextVisible.value) 0F else 1F,
        animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing),
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
            isTextVisible.value = true
            delay(500)
            isItemAppeared.value = true
        }
    } else {
        LaunchedEffect(Unit) {
            isTextVisible.value = false
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