package com.velkonost.getbetter.android.features.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.onboarding.components.OnboardingFifthStep
import com.velkonost.getbetter.android.features.onboarding.components.OnboardingFirstStep
import com.velkonost.getbetter.android.features.onboarding.components.OnboardingForthStep
import com.velkonost.getbetter.android.features.onboarding.components.OnboardingSecondStep
import com.velkonost.getbetter.android.features.onboarding.components.OnboardingThirdStep
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.features.onboarding.presentation.OnboardingViewModel
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val animationDuration = 1200

    val firstStepAnimationEnded = remember { mutableStateOf(false) }
    val secondStepAnimationEnded = remember { mutableStateOf(false) }
    val thirdStepAnimationEnded = remember { mutableStateOf(false) }
    val forthStepAnimationEnded = remember { mutableStateOf(false) }

    val moveTextToBottom = remember { mutableStateOf(false) }
    val buttonVisible = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val textAlpha by animateFloatAsState(
        targetValue = if (
            firstStepAnimationEnded.value || secondStepAnimationEnded.value ||
            thirdStepAnimationEnded.value || forthStepAnimationEnded.value
        ) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutLinearInEasing,
        ),
        label = ""
    )

    val buttonAlpha by animateFloatAsState(
        targetValue = if (buttonVisible.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutLinearInEasing,
        ),
        label = ""
    )

    var positionInRootButton by remember { mutableStateOf(Offset.Zero) }
    var positionInRootText by remember { mutableStateOf(Offset.Zero) }

    LaunchedEffect(moveTextToBottom.value) {
        if (moveTextToBottom.value) {
            delay(1000)
            buttonVisible.value = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(resource = SharedR.colors.main_background))
    ) {

        if (state.affirmation != null) {
            OnboardingFifthStep(
                enable = state.step == 5,
                item = state.affirmation!!,
                text = state.title.toString(LocalContext.current),
            )
        }

        Column {

            Spacer(modifier.height(70.dp))

            Box(
                modifier = modifier.fillMaxHeight(0.7f),
                contentAlignment = Alignment.Center,

                ) {
                OnboardingFirstStep(
                    enable = state.step == 1,
                    moveTextToBottom = moveTextToBottom,
                    animationEnded = firstStepAnimationEnded
                )

                OnboardingSecondStep(
                    enable = state.step == 2,
                    animationEnded = secondStepAnimationEnded,
                    buttonVisible = buttonVisible
                )

                OnboardingThirdStep(
                    enable = state.step == 3,
                    items = state.abilities,
                    animationEnded = thirdStepAnimationEnded
                )

                OnboardingForthStep(
                    enable = state.step == 4,
                    animationEnded = forthStepAnimationEnded
                )
            }

            this.AnimatedVisibility(
                visible = moveTextToBottom.value,
                enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(
                    animationSpec = tween(durationMillis = 1000)
                ),
            ) {
                val height = (positionInRootButton.y - positionInRootText.y)
                val heightDp = with(LocalDensity.current) { height.toDp() - 120.dp }

                Spacer(modifier = modifier.height(heightDp))
            }

            AnimatedContent(targetState = state.title, label = "") {
                Box(
                    modifier = modifier
                        .onGloballyPositioned {
                            if (positionInRootText.y == 0f) {
                                positionInRootText = it.positionInRoot()
                            }
                        }
                        .alpha(textAlpha)
                        .fillMaxWidth()
                        .height(96.dp)
                        .padding(start = 32.dp, end = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.toString(LocalContext.current),
                        style = MaterialTheme.typography.headlineLarge.copy(fontStyle = FontStyle.Italic),
                        color = colorResource(resource = SharedR.colors.text_title),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier.weight(1f))

            AppButton(
                modifier = modifier
                    .alpha(buttonAlpha)
                    .align(Alignment.CenterHorizontally)
                    .onGloballyPositioned {
                        if (positionInRootButton.y == 0f) {
                            positionInRootButton = it.positionInRoot()
                        }
                    },
                labelText = stringResource(resource = SharedR.strings.continue_btn),
                isLoading = false,
                onClick = {
                    scope.launch {
                        firstStepAnimationEnded.value = false
                        secondStepAnimationEnded.value = false
                        thirdStepAnimationEnded.value = false
                        forthStepAnimationEnded.value = false

                        delay(500)
                        viewModel.dispatch(OnboardingAction.NextClick)
                    }
                }
            )
            Spacer(modifier = modifier.height(64.dp))

        }


    }

}