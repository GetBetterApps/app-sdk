package com.velkonost.getbetter.core.compose.components.notelist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.note.SubNote
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun NoteItemData(
    modifier: Modifier = Modifier,
    noteType: NoteType,
    completionDate: String? = null,
    subNotes: List<SubNote>,
    mediaAmount: Int,
    isPrivate: Boolean
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(24.dp)
                .background(
                    colorResource(resource = SharedR.colors.background_item),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp),
            painter = painterResource(
                imageResource = if (noteType == NoteType.Default) SharedR.images.ic_note
                else SharedR.images.ic_goal
            ),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
        )

        AnimatedVisibility(visible = completionDate != null && noteType == NoteType.Goal) {
            Text(
                modifier = modifier
                    .padding(start = 4.dp)
                    .height(24.dp)
                    .background(
                        colorResource(resource = SharedR.colors.background_item),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 4.dp)
                    .wrapContentHeight(),
                text = completionDate ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
        }

        AnimatedVisibility(visible = subNotes.isNotEmpty() && noteType == NoteType.Goal) {
            Text(
                modifier = modifier
                    .padding(start = 4.dp)
                    .height(24.dp)
                    .background(
                        colorResource(resource = SharedR.colors.background_item),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 4.dp)
                    .wrapContentHeight(),
                text = "${subNotes.count { it.completionDate != null }} / ${subNotes.size}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
        }

        Spacer(modifier = modifier.weight(1f))

        AnimatedVisibility(visible = mediaAmount != 0) {
            Row(
                modifier = modifier
                    .padding(end = 4.dp)
                    .height(24.dp)
                    .background(
                        colorResource(resource = SharedR.colors.background_item),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 6.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(end = 2.dp),
                    text = mediaAmount.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )

                Image(
                    modifier = modifier
                        .size(24.dp)
                        .padding(4.dp),
                    painter = painterResource(imageResource = SharedR.images.ic_media),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
                )
            }
        }

        AnimatedVisibility(visible = isPrivate) {
            Image(
                modifier = modifier
                    .size(24.dp)
                    .background(
                        colorResource(resource = SharedR.colors.background_item),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(4.dp),
                painter = painterResource(imageResource = SharedR.images.ic_lock),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
            )
        }

    }

}