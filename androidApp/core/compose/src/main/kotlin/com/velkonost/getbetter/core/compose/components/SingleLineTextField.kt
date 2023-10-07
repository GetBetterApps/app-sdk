package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun SingleLineTextField(
    modifier: Modifier = Modifier,
    value: String,
    isEnabled: Boolean = true,
    textAlign: TextAlign = TextAlign.Start,
    paddingValues: PaddingValues = PaddingValues(start = 12.dp),
    placeholderText: String,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    onValueChanged: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChanged.invoke(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .height(64.dp)
            .clip(shape = MaterialTheme.shapes.medium),
        textStyle = textStyle
            .copy(
                color = colorResource(resource = SharedR.colors.text_secondary),
                textAlign = textAlign
            ),
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
            disabledContainerColor = colorResource(resource = SharedR.colors.text_field_background),
            unfocusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
            focusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
            cursorColor = colorResource(resource = SharedR.colors.text_secondary)
        ),
        enabled = isEnabled
    )
}