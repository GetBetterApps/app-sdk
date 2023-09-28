package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun SingleLineTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String,
    onValueChanged: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChanged.invoke(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
            .height(64.dp)
            .clip(shape = MaterialTheme.shapes.medium),
        textStyle = MaterialTheme.typography.titleMedium
            .copy(color = colorResource(resource = SharedR.colors.text_secondary)),
        maxLines = 1,
        placeholder = {
            Text(
                modifier = modifier.padding(top = 4.dp),
                text = placeholderText,
                color = colorResource(resource = SharedR.colors.hint_color),
                style = MaterialTheme.typography.titleMedium
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
            focusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
            cursorColor = colorResource(resource = SharedR.colors.text_secondary)
        ),
    )
}