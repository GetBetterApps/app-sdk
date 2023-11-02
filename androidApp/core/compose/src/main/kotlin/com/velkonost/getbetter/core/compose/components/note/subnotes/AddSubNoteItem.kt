package com.velkonost.getbetter.core.compose.components.note.subnotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AddSubNoteItem(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String,
    onValueChanged: (String) -> Unit,
    onAddSubNote: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(
                color = colorResource(resource = SharedR.colors.text_field_background),
                shape = MaterialTheme.shapes.medium
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            modifier = modifier.fillMaxWidth(0.8f),
            value = value,
            onValueChange = {
                onValueChanged.invoke(it)
            },

            textStyle = MaterialTheme.typography.titleSmall
                .copy(
                    color = colorResource(resource = SharedR.colors.text_secondary),
                    textAlign = TextAlign.Start
                ),
            maxLines = 1,
            placeholder = {
                Text(
                    modifier = modifier
                        .fillMaxWidth(0.7f)
                        .padding(top = 4.dp),
                    text = placeholderText,
                    color = colorResource(resource = SharedR.colors.hint_color),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Start)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                cursorColor = colorResource(resource = SharedR.colors.text_secondary)
            ),
        )

        Spacer(modifier = modifier.weight(1f))

        Box(
            modifier = modifier
                .padding(end = 12.dp)
                .size(36.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.button_gradient_start),
                    shape = MaterialTheme.shapes.medium
                )
                .align(Alignment.CenterVertically)

        ) {
            Image(
                modifier = modifier
                    .size(24.dp)
                    .align(Alignment.Center)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onAddSubNote.invoke() },
                painter = painterResource(imageResource = SharedR.images.ic_plus),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.text_light))
            )
        }

    }


}