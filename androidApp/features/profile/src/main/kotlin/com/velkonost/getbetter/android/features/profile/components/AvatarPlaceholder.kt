package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AvatarPlaceholder(
    modifier: Modifier,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium,
            )
            .size(128.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(resource = SharedR.colors.button_gradient_start),
                        colorResource(resource = SharedR.colors.button_gradient_end)
                    )
                ),
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick.invoke()
            },
    ) {
        Image(
            modifier = modifier
                .size(64.dp)
                .align(Alignment.Center),
            painter = painterResource(imageResource = SharedR.images.ic_image_placeholder),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = colorResource(resource = SharedR.colors.text_light).copy(alpha = 0.5f)
            )
        )
    }
}