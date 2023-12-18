package com.velkonost.getbetter.android.features.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BoxScope.OnboardingSecondStep(
    modifier: Modifier = Modifier,
    enable: Boolean,
    buttonVisible: MutableState<Boolean>,
    animationEnded: MutableState<Boolean>,
) {

    val animationDuration = 1200
    val durationPerImage = 3500L
    val imageIndex = remember { mutableIntStateOf(0) }

    val rotation by animateFloatAsState(
        targetValue = imageIndex.intValue * 180f,
        animationSpec = tween(
            durationMillis = 800,
            easing = FastOutSlowInEasing,
        ), label = ""
    )

    LaunchedEffect(enable) {
        if (enable) {
            buttonVisible.value = false

            delay(durationPerImage)
            imageIndex.intValue++

            delay(durationPerImage)
            imageIndex.intValue++

//            delay(1500)
            animationEnded.value = true

//            delay(500)
            buttonVisible.value = true

            delay(durationPerImage)
            imageIndex.intValue++

            delay(durationPerImage)
            imageIndex.intValue++


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
                    .graphicsLayer {
                        rotationY = rotation
                        cameraDistance = 12f * density
                    },
            ) {
                Image(
                    modifier = modifier
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .graphicsLayer {
                            rotationY =
                                when {
                                    rotation <= 90f -> 0f
                                    rotation <= 180f -> 180f
                                    rotation <= 360f -> 0f
                                    rotation <= 540f -> 180f
                                    else -> 0f
                                }
                        },
                    painter = painterResource(
                        imageResource =
                        when {
                            rotation <= 90f -> SharedR.images.ic_onboarding_2_1
                            rotation <= 180f -> SharedR.images.ic_onboarding_2_2
                            rotation <= 360f -> SharedR.images.ic_onboarding_2_3
                            rotation <= 540f -> SharedR.images.ic_onboarding_2_4
                            else -> SharedR.images.ic_onboarding_2_5
                        }
                    ),
                    contentDescription = null
                )
            }

        }
    }

}