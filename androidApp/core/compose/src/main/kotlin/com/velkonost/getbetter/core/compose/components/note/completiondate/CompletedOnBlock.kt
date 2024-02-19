package com.velkonost.getbetter.core.compose.components.note.completiondate

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_12
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_60
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource


@Composable
internal fun CompletedOnBlock(
    label: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val viewHeight = remember { DP_60 }
    val labelInnerPadding = remember { DP_12 }
    val textLeadingPadding = remember { DP_16 }
    val labelTrailingPadding = remember { DP_16 }

    val interactionSource = remember { MutableInteractionSource() }

    AnimatedVisibility(visible = label != null) {
        Row(
            modifier = modifier.height(viewHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.padding(start = textLeadingPadding),
                text = stringResource(resource = SharedR.strings.note_detail_completed_goal_title),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
            WeightedSpacer()

            AnimatedContent(
                targetState = label,
                label = ""
            ) { content ->
                if (content != null) {
                    Text(
                        modifier = modifier
                            .padding(end = labelTrailingPadding)
                            .background(
                                color = colorResource(resource = SharedR.colors.button_gradient_start),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(labelInnerPadding)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = onClick
                            ),
                        text = content,
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(resource = SharedR.colors.text_light),
                    )
                }
            }
        }
    }
}