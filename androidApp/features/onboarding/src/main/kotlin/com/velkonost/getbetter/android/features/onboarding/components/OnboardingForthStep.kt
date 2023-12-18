package com.velkonost.getbetter.android.features.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.delay

@Composable
fun BoxScope.OnboardingForthStep(
    modifier: Modifier = Modifier,
    enable: Boolean,
    animationEnded: MutableState<Boolean>,
) {

    val animationDuration = 1200
    val durationPerImage = 3500L
    val imageIndex = remember { mutableIntStateOf(0) }

    val firstImageAlpha by animateFloatAsState(
        targetValue = if (imageIndex.intValue == 0) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutLinearInEasing,
        ),
        label = ""
    )
    val secondImageAlpha by animateFloatAsState(
        targetValue = if (imageIndex.intValue == 1) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutLinearInEasing,
        ),
        label = ""
    )
    val thirdImageAlpha by animateFloatAsState(
        targetValue = if (imageIndex.intValue == 2) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutLinearInEasing,
        ),
        label = ""
    )

    LaunchedEffect(enable) {
        if (enable) {
            delay(durationPerImage)
            imageIndex.intValue++

            delay(durationPerImage)
            imageIndex.intValue++
            animationEnded.value = true
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
            Box(
                modifier = modifier
                    .padding(horizontal = 32.dp)
            ) {
                Image(
                    modifier = modifier
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .alpha(firstImageAlpha),
                    painter = painterResource(imageResource = SharedR.images.ic_onboarding_4_1),
                    contentDescription = null
                )

                Image(
                    modifier = modifier
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .alpha(secondImageAlpha),
                    painter = painterResource(imageResource = SharedR.images.ic_onboarding_4_2),
                    contentDescription = null
                )

                Image(
                    modifier = modifier
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .alpha(thirdImageAlpha),
                    painter = painterResource(imageResource = SharedR.images.ic_onboarding_4_3),
                    contentDescription = null
                )
            }
        }
    }
}