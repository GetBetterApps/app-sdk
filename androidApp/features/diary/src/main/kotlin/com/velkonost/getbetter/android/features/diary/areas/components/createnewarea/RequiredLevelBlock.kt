package com.velkonost.getbetter.android.features.diary.areas.components.createnewarea

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ColumnScope.RequiredLevelRow(
    modifier: Modifier = Modifier,
    title: String,
    level: Int,
    onRequiredLevelChanged: (Int) -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }

    PrimaryBox(padding = 0) {
        Row(
            modifier = modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.padding(start = 16.dp),
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )

            Spacer(modifier = modifier.weight(1f))

            Image(
                modifier = modifier
                    .size(32.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.button_gradient_start),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(4.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onRequiredLevelChanged.invoke(level - 1)
                    },
                painter = painterResource(imageResource = SharedR.images.ic_plus),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_active))
            )

            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                text = level.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_secondary)
            )

            Image(
                modifier = modifier
                    .padding(end = 16.dp)
                    .size(32.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.button_gradient_start),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(4.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onRequiredLevelChanged.invoke(level + 1)
                    },
                painter = painterResource(imageResource = SharedR.images.ic_plus),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_active))
            )
        }
    }
}