package com.velkonost.getbetter.android.features.onboarding

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.features.onboarding.presentation.OnboardingViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {

        Column {

            Spacer(modifier.weight(1f))

            Box {
                androidx.compose.animation.AnimatedVisibility(
                    visible = state.step == 1,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Image(
                        modifier = modifier
                            .shadow(
                                elevation = 8.dp,
                                shape = MaterialTheme.shapes.medium,
                            )
                            .fillMaxHeight(0.6f)
                            .clip(MaterialTheme.shapes.medium),
                        painter = painterResource(imageResource = SharedR.images.ic_onboarding),
                        contentDescription = null
                    )
                }
            }


            Spacer(modifier.weight(1f))

            Text(
                modifier = modifier
                    .padding(start = 32.dp, end = 32.dp)
                    .align(Alignment.CenterHorizontally),
                text = state.title.toString(LocalContext.current),
                style = MaterialTheme.typography.headlineLarge.copy(fontStyle = FontStyle.Italic),
                color = colorResource(resource = SharedR.colors.text_title),
                textAlign = TextAlign.Center
            )
            Spacer(modifier.weight(1f))

            AppButton(
                modifier = modifier.align(Alignment.CenterHorizontally),
                labelText = stringResource(resource = SharedR.strings.continue_btn),
                isLoading = false,
                onClick = {

                }
            )
            Spacer(modifier = modifier.height(64.dp))
        }

    }

}