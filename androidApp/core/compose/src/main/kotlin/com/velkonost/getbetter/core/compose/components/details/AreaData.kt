package com.velkonost.getbetter.core.compose.components.details

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_12
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_32
import com.velkonost.getbetter.core.compose.theme.Pixel.PX_ZERO
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AreaData(
    modifier: Modifier = Modifier,
    area: Area,
    onClick: () -> Unit
) {
    val textWidth = remember { 0.8f }
    val emojiSize = remember { DP_32 }
    val viewPadding = remember { PX_ZERO }
    val viewInnerPadding = remember { DP_16 }
    val textLeadingPadding = remember { DP_12 }

    val interactionSource = remember { MutableInteractionSource() }

    PrimaryBox(padding = viewPadding) {
        Row(
            modifier = modifier
                .padding(viewInnerPadding)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = modifier.size(emojiSize),
                painter = painterResource(imageResource = Emoji.getIconById(area.emojiId!!)),
                contentDescription = null
            )

            Text(
                modifier = modifier
                    .padding(start = textLeadingPadding)
                    .fillMaxWidth(textWidth),
                text = area.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(resource = SharedR.colors.text_primary),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}