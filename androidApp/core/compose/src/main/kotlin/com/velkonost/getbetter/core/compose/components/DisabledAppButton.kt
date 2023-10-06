package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun DisabledAppButton(
    modifier: Modifier = Modifier,
    labelText: String,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(fraction = 0.8f)
            .background(
                color = colorResource(resource = SharedR.colors.button_disabled_color),
                shape = MaterialTheme.shapes.extraLarge
            )
            .height(42.dp),
        enabled = false,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = {

        }
    ) {
        Text(
            text = labelText.uppercase(),
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(resource = SharedR.colors.text_button_disabled),
            textAlign = TextAlign.Center,
        )
    }
}
