package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.tags

import android.view.KeyEvent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun NewTagField(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String,
    onValueChanged: (String) -> Unit,
    onAddNewTag: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledContainerColor = colorResource(resource = SharedR.colors.text_field_background),
        unfocusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
        focusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
        cursorColor = colorResource(resource = SharedR.colors.text_secondary)
    )

    BasicTextField(
        value = value,
        modifier = Modifier
            .defaultMinSize(
                minWidth = 24.dp,
                minHeight = 24.dp
            )
            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode in listOf(
                        KeyEvent.KEYCODE_ENTER,
                        KeyEvent.KEYCODE_SPACE
                    )
                ) {
                    onAddNewTag.invoke()
                }
                false
            },
        onValueChange = onValueChanged,
        enabled = true,
        textStyle = MaterialTheme.typography.bodyMedium.merge(
            TextStyle(
                color = colorResource(
                    resource = SharedR.colors.text_secondary
                )
            )
        ),
        cursorBrush = SolidColor(colorResource(resource = SharedR.colors.text_secondary)),
        singleLine = true,
        maxLines = 1,
        minLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                onAddNewTag.invoke()
            }
        ),
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = placeholderText,
                        color = colorResource(resource = SharedR.colors.hint_color),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
                    )
                },
                label = null,
                shape = MaterialTheme.shapes.small,
                singleLine = true,
                colors = colors,
                enabled = true,
                interactionSource = interactionSource,
                visualTransformation = VisualTransformation.None,
                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 1.dp)
            )
        }
    )
}