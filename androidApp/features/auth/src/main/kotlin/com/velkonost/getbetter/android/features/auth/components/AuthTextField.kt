package com.velkonost.getbetter.android.features.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun AuthTextField(
    modifier: Modifier,
    placeholderText: String,
    value: String,
    inputType: InputType,
    onValueChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .height(56.dp)
            .clip(shape = MaterialTheme.shapes.medium),
        value = value,
        onValueChange = { onValueChanged.invoke(it) },
        textStyle = MaterialTheme.typography.titleMedium
            .copy(color = colorResource(resource = SharedR.colors.text_light)),
        placeholder = {
            Text(
                text = placeholderText,
                color = colorResource(resource = SharedR.colors.onboarding_hint_color),
                style = MaterialTheme.typography.titleMedium
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = colorResource(resource = SharedR.colors.onboarding_text_field),
            focusedContainerColor = colorResource(resource = SharedR.colors.auth_text_field_background),
            cursorColor = colorResource(resource = SharedR.colors.text_light)
        ),

        visualTransformation =
        if (inputType == InputType.Email) VisualTransformation.None
        else PasswordVisualTransformation(),

        keyboardOptions = KeyboardOptions(
            keyboardType =
            if (inputType == InputType.Email) KeyboardType.Email
            else KeyboardType.Password
        )

    )
}

enum class InputType {
    Email,
    Password
}