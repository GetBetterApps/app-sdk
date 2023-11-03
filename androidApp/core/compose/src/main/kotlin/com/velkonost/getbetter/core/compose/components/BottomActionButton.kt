package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun RowScope.BottomActionButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Box(modifier = modifier.weight(1f)) {
        Box(
            modifier = modifier
                .shadow(
                    elevation = 8.dp,
                    shape = MaterialTheme.shapes.small,
                )
                .align(Alignment.Center)
                .size(48.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .clipToBounds()
                .clickable {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick.invoke()
                }
        ) {
            Image(
                modifier = modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                painter = icon,
                colorFilter = ColorFilter.tint(
                    color = colorResource(resource = SharedR.colors.icon_active).copy(alpha = 0.5f)
                ),
                contentDescription = null
            )
        }
    }
}