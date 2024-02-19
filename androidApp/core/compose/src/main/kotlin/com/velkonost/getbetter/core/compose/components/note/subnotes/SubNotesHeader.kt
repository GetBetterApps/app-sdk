package com.velkonost.getbetter.core.compose.components.note.subnotes

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_24
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SubNotesHeader(
    modifier: Modifier = Modifier,
    isSubNotesBlockVisible: MutableState<Boolean>,
) {

    val textWidth = remember { 0.6f }
    val arrowSize = remember { DP_24 }
    val viewMargin = remember { DP_16 }

    val interactionSource = remember { MutableInteractionSource() }
    val areaArrowRotationAngle by animateFloatAsState(
        targetValue = if (isSubNotesBlockVisible.value) -90F else 90F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )

    val haptic = LocalHapticFeedback.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(viewMargin)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                isSubNotesBlockVisible.value = !isSubNotesBlockVisible.value
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = modifier.fillMaxWidth(textWidth),
            text = stringResource(resource = SharedR.strings.create_note_subnote_title),
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            color = colorResource(resource = SharedR.colors.text_primary),
            style = MaterialTheme.typography.titleMedium
        )

        WeightedSpacer()

        Image(
            modifier = modifier
                .size(arrowSize)
                .rotate(areaArrowRotationAngle),
            painter = painterResource(imageResource = SharedR.images.ic_arrow_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = colorResource(resource = SharedR.colors.icon_inactive)
            )
        )
    }

}