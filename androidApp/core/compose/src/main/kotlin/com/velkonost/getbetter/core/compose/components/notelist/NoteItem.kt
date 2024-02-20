package com.velkonost.getbetter.core.compose.components.notelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.components.note.tags.TagItem
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_12
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_20
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_6
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_8
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    item: Note,
    horizontalPadding: Dp = DP_20,
    onClick: (Note) -> Unit,
    onLikeClick: (Note) -> Unit
) {

    val noteTextMaxLines = remember { 10 }
    val tagsItemsPadding = remember { DP_6 }
    val contentTopPadding = remember { DP_12 }
    val contentInnerPadding = remember { DP_8 }
    val noteTextTopPadding = remember { DP_12 }
    val tagsListTopPadding = remember { DP_12 }

    val haptic = LocalHapticFeedback.current
    val context = LocalContext.current

    PrimaryBox(
        modifier = modifier
            .padding(horizontal = horizontalPadding)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick.invoke(item)
            }
    ) {
        Column {
            NoteItemHeader(
                areaName = item.area.name,
                taskName = item.task?.name,
                areaIcon = Emoji.getIconById(item.area.emojiId!!),
                likesData = item.likesData,
                showLikes = !item.isPrivate,
                onLikeClick = {
                    onLikeClick.invoke(item)
                }
            )

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = contentTopPadding)
                    .background(
                        color = colorResource(resource = SharedR.colors.text_field_background),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(contentInnerPadding)
            ) {

                NoteItemData(
                    noteType = item.noteType,
                    subNotes = item.subNotes,
                    expectedCompletionDate = item.expectedCompletionDateStr?.toString(context),
                    expectedCompletionDateExpired = item.expectedCompletionDateExpired,
                    completionDate = item.completionDateStr?.toString(context),
                    mediaAmount = item.mediaUrls.size,
                    isPrivate = item.isPrivate
                )

                Text(
                    modifier = modifier.padding(top = noteTextTopPadding),
                    text = item.text,
                    maxLines = noteTextMaxLines,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(resource = SharedR.colors.text_title)
                )

                FlowRow(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = tagsListTopPadding),
                    horizontalArrangement = Arrangement.spacedBy(tagsItemsPadding),
                    verticalArrangement = Arrangement.spacedBy(tagsItemsPadding)
                ) {
                    item.tags.forEach { tag ->
                        TagItem(tag = TagUI(text = tag))
                    }
                }
            }

        }
    }
}