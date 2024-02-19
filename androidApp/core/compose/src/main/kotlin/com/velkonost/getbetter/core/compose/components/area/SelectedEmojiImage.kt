package com.velkonost.getbetter.core.compose.components.area

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_48
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_8
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun SelectedEmojiImage(
    modifier: Modifier = Modifier,
    selectedEmoji: Emoji,
    imageSize: Dp = DP_48,
    onClick: () -> Unit
) {

    val shadowRadius = remember { DP_8 }
    val innerPadding = remember { DP_8 }
    val itemInnerPadding = remember { DP_8 }

    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .shadow(
                elevation = shadowRadius,
                shape = MaterialTheme.shapes.medium,
            )
            .background(
                color = colorResource(resource = SharedR.colors.background_item),
                shape = MaterialTheme.shapes.medium
            )
            .padding(innerPadding)
    ) {
        AnimatedContent(targetState = selectedEmoji, label = "") {
            Image(
                modifier = modifier
                    .size(imageSize)
                    .background(
                        color = colorResource(resource = SharedR.colors.text_field_background),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(itemInnerPadding)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onClick.invoke()
                    },
                painter = painterResource(imageResource = it.icon),
                contentDescription = null
            )
        }
    }
}