package com.velkonost.getbetter.android.features.notedetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun NoteDetailHeader(
    modifier: Modifier = Modifier,
    isNotePrivate: Boolean,
    onBackClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier.padding(top = 40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(42.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onBackClick() },
            painter = painterResource(imageResource = SharedR.images.ic_arrow_back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = colorResource(resource = SharedR.colors.icon_active)
            )
        )

        Text(
            modifier = modifier.padding(start = 12.dp),
            text = stringResource(resource = SharedR.strings.add_area_title),
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(resource = SharedR.colors.text_title)
        )
        Spacer(modifier.weight(1f))

        if (isNotePrivate) {
            Image(
                modifier = modifier
                    .size(42.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.background_icon),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(4.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onBackClick() },
                painter = painterResource(imageResource = SharedR.images.ic_lock),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = colorResource(resource = SharedR.colors.icon_inactive)
                )
            )
        }
    }
}