package com.velkonost.getbetter.android.features.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
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
        LottieCompositionSpec.RawRes(
            if (step == 1) SharedR.files.anim_mark.rawResId
            else if (step == 2) SharedR.files.anim_onboarding_2.rawResId
            else SharedR.files.loader_new_dark.rawResId
        )
    )

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

        Spacer(modifier.weight(1f))

        LottieAnimation(
            modifier = modifier.height(256.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Text(
            modifier = modifier.padding(top = 12.dp),
            text = title.uppercase(),
            style = MaterialTheme.typography.labelLarge.copy(fontStyle = FontStyle.Italic),
            color = colorResource(resource = SharedR.colors.text_title),
            textAlign = TextAlign.Center
        )

        Spacer(modifier.weight(1f))

        AppButton(
            modifier = modifier
//                .alpha(buttonAlpha)
                .align(Alignment.CenterHorizontally),
            labelText = stringResource(
                resource = if (step == 5) SharedR.strings.onboarding_btn
                else SharedR.strings.continue_btn
            ),
            isLoading = isLoading,
            onClick = onNextClick
        )

        Spacer(modifier = modifier.height(64.dp))

    }


}