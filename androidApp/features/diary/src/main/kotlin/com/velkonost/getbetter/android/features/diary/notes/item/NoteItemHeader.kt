package com.velkonost.getbetter.android.features.diary.notes.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun NoteItemHeader(
    modifier: Modifier = Modifier,
    areaName: String,
    taskName: String? = null,
    areaIcon: ImageResource
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(32.dp),
            painter = painterResource(imageResource = areaIcon),
            contentDescription = null
        )

        Column(
            modifier = modifier.padding(start = 6.dp)
        ) {
            Text(
                modifier = modifier.padding(bottom = 2.dp),
                text = areaName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )

            AnimatedVisibility(visible = taskName != null) {
                Text(
                    modifier = modifier.padding(top = 2.dp),
                    text = taskName!!,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(resource = SharedR.colors.icon_inactive)
                )
            }

        }
    }
}