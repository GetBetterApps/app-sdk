package com.velkonost.getbetter.android.features.diary.notes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.tags.TagItem
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.features.diary.model.TagUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NoteItem(
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current

    val tags = listOf(
        "123",
        "wfpejgrg",
        "fkreog",
        "123",
        "wfpejgrg",
        "fkreog",
        "123",
        "wfpejgrg",
        "fkreog",
        "123",
        "wfpejgrg",
        "fkreog"
    )

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
                    modifier = modifier.size(32.dp),
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
                        color = colorResource(resource = SharedR.colors.icon_inactive)
                    )
                }
            }

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.text_field_background),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp)
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
                        painter = painterResource(imageResource = SharedR.images.ic_goal),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
                    )

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
                        text = "23.11.2023",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )

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
                        text = "4 / 6",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )

                    Spacer(modifier = modifier.weight(1f))

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
                            text = "1",
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

                Text(
                    modifier = modifier.padding(top = 12.dp),
                    text = "С 9 по 13 ноября можно будет бесплатно поиграть в стратегию Anno 1800 на консолях PlayStation 5 и Xbox Series.\n" +
                            "\n" +
                            "Немного раньше «бесплатные выходные» пройдут на ПК, со 2 по 6 ноября.\n" +
                            "\n" +
                            "До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.",
                    maxLines = 10,
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

                    tags.forEach { tag ->
                        TagItem(
                            tag = TagUI(text = tag)
                        )
                    }
                }
            }

        }
    }
}