package com.velkonost.getbetter.core.compose.components.notelist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_2
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_24
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_4
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_6
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.note.SubNote
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun NoteItemData(
    modifier: Modifier = Modifier,
    noteType: NoteType,
    expectedCompletionDate: String? = null,
    expectedCompletionDateExpired: Boolean = false,
    completionDate: String? = null,
    subNotes: List<SubNote>,
    mediaAmount: Int,
    isPrivate: Boolean
) {

    val itemPadding = remember { DP_4 }
    val itemHeight = remember { DP_24 }
    val typeImageSize = remember { DP_24 }
    val privateImageSize = remember { DP_24 }
    val privateImagePadding = remember { DP_4 }
    val typeImageInnerPadding = remember { DP_4 }
    val mediaAmountImageSize = remember { DP_24 }
    val mediaAmountImagePadding = remember { DP_4 }
    val itemHorizontalInnerPadding = remember { DP_4 }
    val mediaAmountTextTrailingPadding = remember { DP_2 }
    val mediaAmountHorizontalInnerPadding = remember { DP_6 }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = modifier
                .size(typeImageSize)
                .background(
                    colorResource(resource = SharedR.colors.background_item),
                    shape = MaterialTheme.shapes.small
                )
                .padding(typeImageInnerPadding),
            painter = painterResource(
                imageResource = if (noteType == NoteType.Default) SharedR.images.ic_note
                else SharedR.images.ic_goal
            ),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
        )

        AnimatedVisibility(
            visible = (expectedCompletionDate != null || completionDate != null)
                    && noteType == NoteType.Goal
        ) {
            Text(
                modifier = modifier
                    .padding(start = itemPadding)
                    .height(itemHeight)
                    .background(
                        colorResource(
                            resource =
                            if (completionDate != null) SharedR.colors.green
                            else if (expectedCompletionDateExpired) SharedR.colors.red
                            else SharedR.colors.background_item
                        ),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = itemHorizontalInnerPadding)
                    .wrapContentHeight(),
                text = completionDate ?: expectedCompletionDate ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(
                    resource =
                    if (completionDate != null || expectedCompletionDateExpired) SharedR.colors.text_light
                    else SharedR.colors.text_primary
                )
            )
        }

        AnimatedVisibility(visible = subNotes.isNotEmpty() && noteType == NoteType.Goal) {
            Text(
                modifier = modifier
                    .padding(start = itemPadding)
                    .height(itemHeight)
                    .background(
                        colorResource(resource = SharedR.colors.background_item),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = itemHorizontalInnerPadding)
                    .wrapContentHeight(),
                text = "${subNotes.count { it.completionDate != null }} / ${subNotes.size}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
        }

        WeightedSpacer()

        AnimatedVisibility(visible = mediaAmount != 0) {
            Row(
                modifier = modifier
                    .padding(end = itemPadding)
                    .height(itemHeight)
                    .background(
                        colorResource(resource = SharedR.colors.background_item),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = mediaAmountHorizontalInnerPadding)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(end = mediaAmountTextTrailingPadding),
                    text = mediaAmount.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )

                Image(
                    modifier = modifier
                        .size(mediaAmountImageSize)
                        .padding(mediaAmountImagePadding),
                    painter = painterResource(imageResource = SharedR.images.ic_media),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
                )
            }
        }

        AnimatedVisibility(visible = isPrivate) {
            Image(
                modifier = modifier
                    .size(privateImageSize)
                    .background(
                        colorResource(resource = SharedR.colors.background_item),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(privateImagePadding),
                painter = painterResource(imageResource = SharedR.images.ic_lock),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
            )
        }

    }

}