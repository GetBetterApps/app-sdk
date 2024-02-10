package com.velkonost.getbetter.android.features.subscription

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.velkonost.getbetter.core.compose.components.Logo
import com.velkonost.getbetter.shared.features.subscription.presentation.SubscriptionViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SubscriptionScreen(
    modifier: Modifier = Modifier,
    viewModel: SubscriptionViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()

    val firstPointVisible = remember { mutableStateOf(false) }
    val secondPointVisible = remember { mutableStateOf(false) }
    val thirdPointVisible = remember { mutableStateOf(false) }
    val forthPointVisible = remember { mutableStateOf(false) }
    val fifthPointVisible = remember { mutableStateOf(false) }
    val sixthPointVisible = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            delay(500)
            firstPointVisible.value = true
            delay(500)
            secondPointVisible.value = true
            delay(500)
            thirdPointVisible.value = true
            delay(500)
            forthPointVisible.value = true
            delay(500)
            fifthPointVisible.value = true
            delay(500)
            sixthPointVisible.value = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(resource = SharedR.colors.onboarding_background_gradient_start),
                        colorResource(resource = SharedR.colors.onboarding_background_gradient_end)
                    )
                )
            )
            .padding(start = 16.dp, end = 16.dp)
    ) {

        Column(modifier = modifier.padding(top = 50.dp)) {
            Row(modifier = modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(imageResource = SharedR.images.ic_close),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = colorResource(resource = SharedR.colors.text_light).copy(
                            alpha = 0.4f
                        )
                    )
                )
                Spacer(modifier.weight(1f))

                Text(
                    text = stringResource(resource = SharedR.strings.paywall_restore),
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(resource = SharedR.colors.text_light).copy(alpha = 0.4f)
                )
            }

            Logo(modifier = modifier.padding(top = 16.dp))

            Row(modifier = modifier.padding(top = 16.dp)) {
                Spacer(modifier.weight(1f))
                Text(
                    text = stringResource(resource = SharedR.strings.paywall_title),
                    color = colorResource(resource = SharedR.colors.text_light),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier.weight(1f))
            }

            AnimatedVisibility(visible = firstPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_1),
                    visible = firstPointVisible.value
                )
            }

            AnimatedVisibility(visible = secondPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_2),
                    visible = secondPointVisible.value
                )
            }

            AnimatedVisibility(visible = thirdPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_3),
                    visible = thirdPointVisible.value
                )
            }

            AnimatedVisibility(visible = forthPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_4),
                    visible = forthPointVisible.value
                )
            }

            AnimatedVisibility(visible = fifthPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_5),
                    visible = fifthPointVisible.value
                )
            }

            AnimatedVisibility(visible = sixthPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_6),
                    visible = sixthPointVisible.value
                )
            }

        }
    }

}

@Composable
fun SubscriptionPoint(
    modifier: Modifier = Modifier,
    title: String,
    visible: Boolean
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(SharedR.files.anim_mark.rawResId)
    )

    val scope = rememberCoroutineScope()
    val animVisible = remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if (visible) {
            scope.launch {
                delay(500)
                animVisible.value = true
            }
        }
    }

    Row(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (animVisible.value) {
            LottieAnimation(
                modifier = modifier.size(48.dp),
                composition = composition,
                iterations = 1,
            )
        } else {
            Spacer(modifier.size(48.dp))
        }

        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(resource = SharedR.colors.text_title),
            textAlign = TextAlign.Start,
        )

        Spacer(modifier.weight(1f))
    }
}