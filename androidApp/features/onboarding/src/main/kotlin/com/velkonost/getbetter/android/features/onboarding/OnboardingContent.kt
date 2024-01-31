package com.velkonost.getbetter.android.features.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnboardingContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    title: String,
    step: Int,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(SharedR.files.anim_onboarding_1.rawResId)
    )

    val firstPointVisible = remember { mutableStateOf(false) }
    val secondPointVisible = remember { mutableStateOf(false) }
    val thirdPointVisible = remember { mutableStateOf(false) }
    val forthPointVisible = remember { mutableStateOf(false) }
    val fifthPointVisible = remember { mutableStateOf(false) }
    val buttonVisible = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            delay(2000)
            firstPointVisible.value = true
            delay(2000)
            secondPointVisible.value = true
            delay(2000)
            thirdPointVisible.value = true
            delay(2000)
            forthPointVisible.value = true
            delay(2000)
            fifthPointVisible.value = true
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier.height(50.dp))

        Row {
            Spacer(modifier.weight(1f))
            Text(
                modifier = modifier
                    .padding(end = 16.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onSkipClick
                    ),
                text = stringResource(resource = SharedR.strings.skip_btn),
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
        }

        Spacer(modifier.height(24.dp))

        LottieAnimation(
            modifier = modifier.height(256.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Spacer(modifier.height(32.dp))

        AnimatedContent(targetState = firstPointVisible, label = "") {
            if (it.value) {
                OnboardingPoint(title = stringResource(resource = SharedR.strings.onboarding_step_1))
            }
        }

        AnimatedContent(targetState = secondPointVisible, label = "") {
            if (it.value) {
                OnboardingPoint(title = stringResource(resource = SharedR.strings.onboarding_step_2))
            }
        }

        AnimatedContent(targetState = thirdPointVisible, label = "") {
            if (it.value) {
                OnboardingPoint(title = stringResource(resource = SharedR.strings.onboarding_step_3))
            }
        }

        AnimatedContent(targetState = forthPointVisible, label = "") {
            if (it.value) {
                OnboardingPoint(title = stringResource(resource = SharedR.strings.onboarding_step_4))
            }
        }

        AnimatedContent(targetState = fifthPointVisible, label = "") {
            if (it.value) {
                OnboardingPoint(title = stringResource(resource = SharedR.strings.onboarding_step_5))
            }
        }

        Spacer(modifier.weight(1f))

        AnimatedContent(targetState = buttonVisible, label = "") {
            if (it.value) {
                AppButton(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally),
                    labelText = stringResource(resource = SharedR.strings.onboarding_btn),
                    isLoading = isLoading,
                    onClick = onNextClick
                )
            }
        }

        Spacer(modifier = modifier.height(64.dp))

    }
}

@Composable
fun OnboardingPoint(
    modifier: Modifier = Modifier,
    title: String
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(SharedR.files.anim_mark.rawResId)
    )

    val scope = rememberCoroutineScope()
    val animVisible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        scope.launch {
            delay(1500)
            animVisible.value = true
        }
    }

    Row(
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        if (animVisible.value) {
            LottieAnimation(
                modifier = modifier.size(24.dp),
                composition = composition,
                iterations = 1,
            )
        } else {
            Spacer(modifier.size(24.dp))
        }

        Text(
            modifier = modifier.padding(start = 8.dp),
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(resource = SharedR.colors.text_title),
            textAlign = TextAlign.Start,
        )
    }

}