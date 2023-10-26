package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.tags

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
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.features.diary.model.TagUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun RowScope.TagItem(
    modifier: Modifier = Modifier,
    tag: TagUI,
    onTagDelete: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .height(24.dp)
            .align(Alignment.CenterVertically)
            .background(
                color = colorResource(resource = SharedR.colors.button_gradient_start),
                shape = MaterialTheme.shapes.small
            )
            .padding(
                start = 6.dp,
                end = 6.dp,
                top = 3.dp,
                bottom = 3.dp
            ),
    ) {
        Text(
            modifier = modifier
                .height(24.dp)
                .align(Alignment.CenterVertically),
            text = tag.text,
            textAlign = TextAlign.Center,
            color = colorResource(resource = SharedR.colors.text_secondary),
            style = MaterialTheme.typography.bodyMedium
        )

        Image(
            modifier = modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterVertically)
                .size(14.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onTagDelete.invoke(tag.text)
                },
            painter = painterResource(imageResource = SharedR.images.ic_close),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = colorResource(resource = SharedR.colors.text_secondary)
            )
        )
    }
}