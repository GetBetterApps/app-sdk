package com.velkonost.getbetter.android.features.diary.notes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun NoteItem(
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current

    PrimaryBox(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = modifier.size(24.dp),
                    painter = painterResource(imageResource = Emoji.Emoji1.icon),
                    contentDescription = null
                )

                Column(
                    modifier = modifier.padding(start = 6.dp)
                ) {
                    Text(
                        modifier = modifier.padding(bottom = 2.dp),
                        text = "Very long and interesting area name bla bla bla",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )

                    Text(
                        modifier = modifier.padding(top = 2.dp),
                        text = "difficult and amazing task name oalalalallalalala",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )
                }
            }

        }
    }
}