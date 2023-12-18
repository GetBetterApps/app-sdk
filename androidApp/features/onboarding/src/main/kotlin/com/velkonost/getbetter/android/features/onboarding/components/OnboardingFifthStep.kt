package com.velkonost.getbetter.android.features.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun BoxScope.OnboardingFifthStep(
    modifier: Modifier = Modifier,
    item: Affirmation,
    text: String,
    enable: Boolean
) {
    val animationDuration = 1200
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

    LaunchedEffect(enable) {
        if (enable) {
            delay(1200)
            isTextVisible.value = true
            delay(1000)
            isItemAppeared.value = true
        }
    }

    Column(
        modifier
            .fillMaxWidth()
            .align(Alignment.Center)
    ) {
        AnimatedVisibility(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            visible = enable,
            enter = fadeIn(
                tween(
                    durationMillis = animationDuration,
                    easing = LinearEasing
                )
            ),
            exit = fadeOut(
                tween(
                    durationMillis = animationDuration,
                    easing = LinearEasing
                )
            )
        ) {
            Box {
                CoilImage(
                    modifier = modifier
                        .fillMaxSize()
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
                        .fillMaxSize()
                        .alpha(imageAlpha)
                        .scale(if (enable) imageScale else 1f),
                    model = ImageRequest
                        .Builder(context)
                        .data(item.imageUrl)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )

                OnboardingAffirmationText(
                    modifier = modifier.alpha(textAlpha),
                    text = text
                )
            }
        }
    }

}