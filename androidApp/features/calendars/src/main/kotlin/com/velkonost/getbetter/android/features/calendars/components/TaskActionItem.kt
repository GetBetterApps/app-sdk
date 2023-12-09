package com.velkonost.getbetter.android.features.calendars.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.components.note.tags.TagItem
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskActionItem(
    modifier: Modifier = Modifier,
    item: TaskUI,
    onClick: () -> Unit,

    ) {

    val haptic = LocalHapticFeedback.current
    val context = LocalContext.current

    PrimaryBox(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
    ) {
        Box {
            Column(
                modifier = modifier.alpha(
                    if (item.isCompleted) 0.2f else 1f
                )
            ) {
                TaskItemHeader(
                    areaName = item.area.name,
                    taskName = item.name,
                    areaIcon = Emoji.getIconById(item.area.emojiId!!),
                    isFavorite = item.isFavorite,
                    onFavoriteClick = onFavoriteClick,
                    isFavoriteLoading = item.isFavoriteLoading
                )

                Text(
                    modifier = modifier.padding(top = 12.dp),
                    text = item.why,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(resource = SharedR.colors.text_title)
                )

                FlowRow(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    item.abilities.forEach { ability ->
                        TagItem(tag = TagUI(text = ability.name))
                    }
                }
            }
        }
    }

}