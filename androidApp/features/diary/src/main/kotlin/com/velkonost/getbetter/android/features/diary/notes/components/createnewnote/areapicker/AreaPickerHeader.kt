package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.areapicker

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.NoteType
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import model.Area

@Composable
fun AreaPickerHeader(
    modifier: Modifier = Modifier,
    selectedArea: Area?,
    isAreaPickerVisible: MutableState<Boolean>,
    noteType: NoteType
) {
    val interactionSource = remember { MutableInteractionSource() }
    val areaArrowRotationAngle by animateFloatAsState(
        targetValue = if (isAreaPickerVisible.value) -90F else 90F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                isAreaPickerVisible.value = !isAreaPickerVisible.value
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedContent(targetState = selectedArea, label = "") { area ->
            if (area == null) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(
                        resource =
                        if (noteType == NoteType.Default) SharedR.strings.select_area_for_note
                        else SharedR.strings.select_area_for_goal
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(resource = SharedR.colors.text_primary),
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = modifier.size(32.dp),
                        painter = painterResource(imageResource = Emoji.getIconById(area.emojiId!!)),
                        contentDescription = null
                    )

                    Text(
                        modifier = modifier.padding(start = 12.dp),
                        text = area.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = colorResource(resource = SharedR.colors.text_primary),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }


        Spacer(modifier = modifier.weight(1f))

        Image(
            modifier = modifier
                .size(24.dp)
                .rotate(areaArrowRotationAngle),
            painter = painterResource(imageResource = SharedR.images.ic_arrow_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = colorResource(resource = SharedR.colors.icon_inactive)
            )
        )
    }
}