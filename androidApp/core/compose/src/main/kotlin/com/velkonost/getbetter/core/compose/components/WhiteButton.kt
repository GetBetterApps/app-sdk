package com.velkonost.getbetter.core.compose.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun ColumnScope.WhiteButton(
    modifier: Modifier,
    labelText: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.extraLarge,
            )
            .fillMaxWidth(fraction = 0.8f)
            .background(
                color = colorResource(resource = SharedR.colors.text_light),
                shape = MaterialTheme.shapes.extraLarge
            )
            .align(Alignment.CenterHorizontally)
            .height(64.dp),
        enabled = !isLoading,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = { onClick.invoke() }
    ) {

        if (isLoading) {
            CircularProgressIndicator(
                modifier = modifier.size(32.dp),
                color = colorResource(resource = SharedR.colors.text_button_enabled)
            )
        } else {
            AnimatedContent(
                targetState = labelText,
                transitionSpec = {
                    fadeIn(animationSpec = tween(durationMillis = 500)) togetherWith
                            fadeOut(animationSpec = tween(durationMillis = 500))
                },
                contentAlignment = Alignment.Center, label = ""
            ) { text ->
                Text(
                    text = text.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(resource = SharedR.colors.text_button_enabled),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}