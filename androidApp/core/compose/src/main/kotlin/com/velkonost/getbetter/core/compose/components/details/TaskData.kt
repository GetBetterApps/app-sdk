package com.velkonost.getbetter.core.compose.components.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Pixel
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun TaskData(
    modifier: Modifier = Modifier,
    task: TaskUI,
    onClick: () -> Unit
) {
    val viewPadding = remember { Pixel.PX_ZERO }
    val viewInnerPadding = remember { DP_16 }

    val interactionSource = remember { MutableInteractionSource() }

    PrimaryBox(padding = viewPadding) {
        Row(
            modifier = modifier
                .padding(viewInnerPadding)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = task.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(resource = SharedR.colors.text_primary),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}