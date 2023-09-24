package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    labelText: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Button(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.extraLarge,
            )
            .fillMaxWidth(fraction = 0.8f)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(resource = SharedR.colors.button_gradient_start),
                        colorResource(resource = SharedR.colors.button_gradient_end)
                    )
                ),
                shape = MaterialTheme.shapes.extraLarge
            )
            .height(42.dp),
        enabled = !isLoading,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick.invoke()
        }
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = modifier.size(24.dp),
                color = colorResource(resource = SharedR.colors.text_light)
            )
        } else {
            Text(
                text = labelText.uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_light),
                textAlign = TextAlign.Center,
            )
        }
    }

}