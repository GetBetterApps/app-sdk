package com.velkonost.getbetter.android.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun WisdomItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    backgroundImage: Painter,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Box(
        modifier = modifier
            .padding(top = 16.dp)
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium,
            )
            .fillMaxWidth()
            .height(128.dp)
            .background(
                color = colorResource(resource = SharedR.colors.background_item),
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick.invoke()
            }

    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .background(
                    color = colorResource(
                        resource = SharedR.colors.background_item
                    ).copy(alpha = 0.4f),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp)
        ) {
            Text(
                text = title,
                color = colorResource(resource = SharedR.colors.text_title),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )

            Text(
                text = description,
                color = colorResource(resource = SharedR.colors.text_regular_title),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )

        }

    }
}