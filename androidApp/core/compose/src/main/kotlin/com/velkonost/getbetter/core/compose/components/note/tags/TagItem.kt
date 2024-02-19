package com.velkonost.getbetter.core.compose.components.note.tags

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_14
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_24
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_4
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_6
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun RowScope.TagItem(
    modifier: Modifier = Modifier,
    tag: TagUI,
    onlyView: Boolean = false,
    onTagDelete: ((String) -> Unit)? = null
) {

    val textHeight = remember { DP_24 }
    val itemHeight = remember { DP_24 }
    val closeImageSize = remember { DP_14 }
    val itemVerticalPadding = remember { DP_4 }
    val itemHorizontalPadding = remember { DP_6 }
    val closeImageLeadingPadding = remember { DP_4 }

    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .height(itemHeight)
            .align(Alignment.CenterVertically)
            .background(
                color = colorResource(resource = SharedR.colors.button_gradient_start),
                shape = MaterialTheme.shapes.small
            )
            .padding(
                vertical = itemVerticalPadding,
                horizontal = itemHorizontalPadding,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = modifier
                .height(textHeight)
                .align(Alignment.CenterVertically),
            text = tag.text,
            textAlign = TextAlign.Center,
            color = colorResource(resource = SharedR.colors.text_light),
            style = MaterialTheme.typography.bodySmall,
        )

        if (onTagDelete != null && !onlyView) {
            Image(
                modifier = modifier
                    .padding(start = closeImageLeadingPadding)
                    .align(Alignment.CenterVertically)
                    .size(closeImageSize)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onTagDelete.invoke(tag.text)
                    },
                painter = painterResource(imageResource = SharedR.images.ic_close),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = colorResource(resource = SharedR.colors.text_light)
                )
            )
        }
    }
}