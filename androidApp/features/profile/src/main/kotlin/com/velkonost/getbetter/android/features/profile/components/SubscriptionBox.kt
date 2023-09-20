package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SubscriptionBox(
    modifier: Modifier = Modifier,
    subscriptionPlan: String,
    onUpgradeClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .padding(top = 32.dp)
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium,
            )
            .fillMaxWidth()
            .background(
                color = colorResource(resource = SharedR.colors.background_item),
                shape = MaterialTheme.shapes.medium
            )
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp)
    ) {

        Column {
            Text(
                modifier = modifier.padding(bottom = 4.dp),
                text = subscriptionPlan,
                color = colorResource(resource = SharedR.colors.text_title),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                modifier = modifier.padding(top = 4.dp),
                text = stringResource(resource = SharedR.strings.profile_sub_active_plan).uppercase(),
                color = colorResource(resource = SharedR.colors.text_secondary).copy(alpha = 0.5f),
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(modifier = modifier.weight(1f))

        Text(
            modifier = modifier
                .shadow(
                    elevation = 2.dp,
                    shape = MaterialTheme.shapes.extraLarge,
                )
                .align(Alignment.CenterVertically)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(resource = SharedR.colors.button_gradient_start),
                            colorResource(resource = SharedR.colors.button_gradient_end)
                        )
                    ),
                    shape = MaterialTheme.shapes.extraLarge
                )
                .padding(top = 6.dp, bottom = 6.dp, end = 16.dp, start = 16.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onUpgradeClick.invoke()
                },
            text = stringResource(resource = SharedR.strings.profile_sub_upgrade).uppercase(),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(resource = SharedR.colors.text_light),
            textAlign = TextAlign.End
        )

    }
}