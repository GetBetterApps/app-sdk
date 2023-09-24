package com.velkonost.getbetter.android.features.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ColumnScope.AuthAnonymousButton(
    modifier: Modifier,
    isEnabled: Boolean,
    haptic: HapticFeedback,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Text(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.extraLarge,
            )
            .align(Alignment.End)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(resource = SharedR.colors.button_gradient_start),
                        colorResource(resource = SharedR.colors.button_gradient_end)
                    )
                ),
                shape = MaterialTheme.shapes.extraLarge
            )
            .padding(top = 6.dp, bottom = 6.dp, end = 32.dp, start = 32.dp)
            .clickable(
                enabled = isEnabled,
                interactionSource = interactionSource,
                indication = null
            ) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick.invoke()
            },
        text = stringResource(resource = SharedR.strings.auth_later),
        style = MaterialTheme.typography.titleSmall,
        color = colorResource(resource = SharedR.colors.text_light),
        textAlign = TextAlign.End
    )
}