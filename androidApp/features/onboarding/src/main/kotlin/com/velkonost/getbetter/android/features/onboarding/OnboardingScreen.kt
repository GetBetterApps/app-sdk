package com.velkonost.getbetter.android.features.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.onboarding.components.OnboardingFirstStep
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.features.onboarding.presentation.OnboardingViewModel
import com.velkonost.getbetter.shared.features.onboarding.presentation.contract.OnboardingAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val animationDuration = 1200

    val moveTextToBottom = remember { mutableStateOf(false) }
    val firstStepAnimationEnded = remember { mutableStateOf(false) }

    val textAlpha by animateFloatAsState(
        targetValue = if (firstStepAnimationEnded.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = FastOutLinearInEasing,
            delayMillis = 500
        ),
        label = ""
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(resource = SharedR.colors.background_item))
    ) {

        Column {

            Spacer(modifier.weight(1f))

            Box(
                contentAlignment = Alignment.Center
            ) {
                OnboardingFirstStep(
                    enable = state.step == 1,
                    moveTextToBottom = moveTextToBottom,
                    animationEnded = firstStepAnimationEnded
                )

                Column(modifier.fillMaxWidth()) {
                    AnimatedVisibility(
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        visible = state.step == 2,
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
                        Image(
                            modifier = modifier
                                .shadow(
                                    elevation = 8.dp,
                                    shape = MaterialTheme.shapes.medium,
                                )
                                .fillMaxHeight(0.6f)
                                .clip(MaterialTheme.shapes.medium)
                                .align(Alignment.CenterHorizontally),
                            painter = painterResource(imageResource = SharedR.images.ic_onboarding_2),
                            contentDescription = null
                        )
                    }
                }
            }


            Column {
                this.AnimatedVisibility(visible = moveTextToBottom.value) {
                    Spacer(modifier = modifier.height(48.dp))
                }
                Box(
                    modifier = modifier
                        .alpha(textAlpha)
                        .fillMaxWidth()
                        .height(96.dp)
                        .padding(start = 32.dp, end = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.title.toString(LocalContext.current),
                        style = MaterialTheme.typography.headlineLarge.copy(fontStyle = FontStyle.Italic),
                        color = colorResource(resource = SharedR.colors.text_title),
                        textAlign = TextAlign.Center
                    )
                }
            }

//            AnimatedContent(
//                transitionSpec = {
//                    (slideInVertically() togetherWith slideOutVertically())
//                },
//                targetState = moveTextToBottom.value, label = "") {


//            AnimatedContent(targetState = state.title, label = "") {


//            }

//                }

//            }

            Spacer(modifier.weight(1f))

            AppButton(
                modifier = modifier.align(Alignment.CenterHorizontally),
                labelText = stringResource(resource = SharedR.strings.continue_btn),
                isLoading = false,
                onClick = {
                    viewModel.dispatch(OnboardingAction.NextClick)
                }
            )
            Spacer(modifier = modifier.height(64.dp))

        }

    }

}