package com.velkonost.getbetter.android.features.diary.areas.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AreaItem(
    item: Area,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
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
                onClick.invoke(item.id)
            }
    ) {
        Row {
            if (item.emojiId != null) {
                Image(
                    modifier = modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(imageResource = Emoji.getIconById(item.emojiId!!)),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = item.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(resource = SharedR.colors.text_title),
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    modifier = modifier.padding(top = 4.dp),
                    text = item.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(resource = SharedR.colors.text_secondary),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier.weight(1f))

            AnimatedVisibility(
                visible = item.isPrivate
            ) {
                Column {
                    Image(
                        modifier = modifier
                            .size(24.dp)
                            .background(
                                colorResource(resource = SharedR.colors.main_background),
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(4.dp),
                        painter = painterResource(imageResource = SharedR.images.ic_lock),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
                    )
                    Spacer(modifier.weight(1f))
                }

            }
        }
    }
}