package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun HintButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Image(
        modifier = modifier
            .size(18.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        painter = painterResource(imageResource = SharedR.images.ic_info),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
    )

}