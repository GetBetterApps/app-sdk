package com.velkonost.getbetter.core.compose.components.note.completiondate

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource


@Composable
internal fun CompletedOnBlock(
    label: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    AnimatedVisibility(visible = label != null) {
        Row(
            modifier = modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.padding(start = 16.dp),
                text = stringResource(resource = SharedR.strings.note_detail_completed_goal_title),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
            Spacer(modifier = modifier.weight(1f))

            AnimatedContent(
                targetState = label,
                label = ""
            ) { content ->
                if (content != null) {
                    Text(
                        modifier = modifier
                            .padding(end = 16.dp)
                            .background(
                                color = colorResource(resource = SharedR.colors.button_gradient_start),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(12.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                onClick()
                            },
                        text = content,
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(resource = SharedR.colors.text_light),
                    )
                }
            }
        }
    }
}