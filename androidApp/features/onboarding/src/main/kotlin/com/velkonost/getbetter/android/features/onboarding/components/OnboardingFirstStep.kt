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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
fun OnboardingFirstStep(
    modifier: Modifier = Modifier,
    enable: Boolean,
    moveTextToBottom: MutableState<Boolean>
) {
    val animationDuration = 1200

    val firstImageShouldShow = remember { mutableStateOf(false) }
    val firstImageShown = remember { mutableStateOf(false) }

    val firstImageAlpha by animateFloatAsState(
        targetValue = if (firstImageShouldShow.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val firstImageTopPadding by animateFloatAsState(
        targetValue = if (firstImageShown.value) 190F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val firstImageStartPadding by animateFloatAsState(
        targetValue = if (firstImageShown.value) 190F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )

    val secondImageShouldShow = remember { mutableStateOf(false) }
    val secondImageShown = remember { mutableStateOf(false) }

    val secondImageAlpha by animateFloatAsState(
        targetValue = if (secondImageShouldShow.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val secondImageTopPadding by animateFloatAsState(
        targetValue = if (secondImageShown.value) 170F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val secondImageStartPadding by animateFloatAsState(
        targetValue = if (secondImageShown.value) 170F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val secondImageEndPadding by animateFloatAsState(
        targetValue = if (secondImageShown.value) 10F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )

    val thirdImageShouldShow = remember { mutableStateOf(false) }
    val thirdImageShown = remember { mutableStateOf(false) }
    val thirdImageAlpha by animateFloatAsState(
        targetValue = if (thirdImageShouldShow.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val thirdImageTopPadding by animateFloatAsState(
        targetValue = if (thirdImageShown.value) 140F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val thirdImageStartPadding by animateFloatAsState(
        targetValue = if (thirdImageShown.value) 150F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val thirdImageEndPadding by animateFloatAsState(
        targetValue = if (thirdImageShown.value) 20F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )

    val forthImageShouldShow = remember { mutableStateOf(false) }
    val forthImageShown = remember { mutableStateOf(false) }
    val forthImageAlpha by animateFloatAsState(
        targetValue = if (forthImageShouldShow.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val forthImageTopPadding by animateFloatAsState(
        targetValue = if (forthImageShown.value) 130F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val forthImageStartPadding by animateFloatAsState(
        targetValue = if (forthImageShown.value) 130F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )
    val forthImageEndPadding by animateFloatAsState(
        targetValue = if (forthImageShown.value) 30F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )

    val fifthImageShouldShow = remember { mutableStateOf(false) }
    val fifthImageShown = remember { mutableStateOf(false) }
    val fifthImageAlpha by animateFloatAsState(
        targetValue = if (fifthImageShouldShow.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )

    val imagesBlockShow = remember { mutableStateOf(true) }
    val durationPerImage = 500L

    LaunchedEffect(Unit) {
        firstImageShouldShow.value = true
        delay(durationPerImage)
        firstImageShown.value = true
        delay(500)

        secondImageShouldShow.value = true
        delay(durationPerImage)
        secondImageShown.value = true
        delay(500)

        thirdImageShouldShow.value = true
        delay(durationPerImage)
        thirdImageShown.value = true
        delay(500)

        forthImageShouldShow.value = true
        delay(durationPerImage)
        forthImageShown.value = true
        delay(500)

        fifthImageShouldShow.value = true
        delay(durationPerImage)
        fifthImageShown.value = true
        delay(500)

        imagesBlockShow.value = false
        delay(3500)
        moveTextToBottom.value = true
    }

    Column(modifier.fillMaxWidth()) {
        AnimatedVisibility(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            visible = imagesBlockShow.value,
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {

                Image(
                    modifier = modifier
                        .alpha(firstImageAlpha)
                        .padding(top = firstImageTopPadding.dp, start = firstImageStartPadding.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .width(300.dp),
                    painter = painterResource(imageResource = SharedR.images.ic_onboarding_1_3),
                    contentDescription = null
                )
                Image(
                    modifier = modifier
                        .alpha(secondImageAlpha)
                        .padding(
                            top = secondImageTopPadding.dp,
                            start = secondImageStartPadding.dp,
                            end = secondImageEndPadding.dp
                        )
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .width(300.dp),
                    painter = painterResource(imageResource = SharedR.images.ic_onboarding_1_2),
                    contentDescription = null
                )

                Image(
                    modifier = modifier
                        .alpha(thirdImageAlpha)
                        .padding(
                            top = thirdImageTopPadding.dp,
                            start = thirdImageStartPadding.dp,
                            end = thirdImageEndPadding.dp
                        )
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .width(300.dp),
                    painter = painterResource(imageResource = SharedR.images.ic_onboarding_1_1),
                    contentDescription = null
                )

                Image(
                    modifier = modifier
                        .alpha(forthImageAlpha)
                        .padding(
                            top = forthImageTopPadding.dp,
                            start = forthImageStartPadding.dp,
                            end = forthImageEndPadding.dp
                        )
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .width(300.dp),
                    painter = painterResource(imageResource = SharedR.images.ic_onboarding_1_4),
                    contentDescription = null
                )

                Image(
                    modifier = modifier
                        .alpha(fifthImageAlpha)
//                        .padding(top = forthImageTopPadding.dp, start = forthImageStartPadding.dp, end = forthImageEndPadding.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .width(300.dp),
                    painter = painterResource(imageResource = SharedR.images.ic_onboarding_1_4),
                    contentDescription = null
                )
            }
        }
    }
}