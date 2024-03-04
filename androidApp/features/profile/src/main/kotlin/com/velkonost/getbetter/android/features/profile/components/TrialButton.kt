package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TrialButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onClick: () -> Unit
) {

    val haptic = LocalHapticFeedback.current

    PrimaryBox(
        isBright = true,
        padding = 0
    ) {

        Row(
            modifier = modifier
                .fillMaxSize()
//                .clipToBounds()

                .clip(MaterialTheme.shapes.medium)
                .clickable(
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onClick()
                    }
                )
        ) {
            WeightedSpacer()
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .size(24.dp),
                    color = colorResource(resource = SharedR.colors.text_light)
                )
            } else {
                Text(
                    modifier = modifier.padding(vertical = 12.dp),
                    text = stringResource(resource = SharedR.strings.start_trial_button).uppercase(),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(resource = SharedR.colors.text_light)
                )
            }
            WeightedSpacer()
        }
    }
}