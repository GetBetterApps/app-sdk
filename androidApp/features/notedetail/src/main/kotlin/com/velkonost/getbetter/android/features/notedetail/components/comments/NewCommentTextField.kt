package com.velkonost.getbetter.android.features.notedetail.components.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun NewCommentTextField(
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    value: String,
    placeholderText: String,
    isEnabled: Boolean = true,
    textAlign: TextAlign = TextAlign.Start,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    onValueChanged: (String) -> Unit,
    onSendClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column {
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            colorResource(resource = SharedR.colors.main_background)
                                .copy(alpha = 0.7f),
                            colorResource(resource = SharedR.colors.main_background),
                        ),
                    ),
                )
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(horizontal = 12.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.main_background),
                )
                .padding(bottom = 50.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = MaterialTheme.shapes.medium,
                )
                .clip(MaterialTheme.shapes.medium),
        ) {
            TextField(
                value = value,
                onValueChange = {
                    onValueChanged.invoke(it)
                },
                modifier = modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentHeight(),
                textStyle = textStyle
                    .copy(
                        color = colorResource(resource = SharedR.colors.text_secondary),
                        textAlign = textAlign
                    ),
                minLines = minLines,
                placeholder = {
                    Text(
                        modifier = modifier
//                        .fillMaxWidth()
                            .padding(top = 4.dp),
                        text = placeholderText,
                        color = colorResource(resource = SharedR.colors.hint_color),
                        textAlign = textAlign,
                        style = MaterialTheme.typography.titleMedium.copy(textAlign = textAlign)
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

            Box(
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .background(
                        color = colorResource(resource = SharedR.colors.button_gradient_start)
                    )
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onSendClick
                    )
            ) {
                Image(
                    modifier = modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(imageResource = SharedR.images.ic_send),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.text_light))
                )
            }
        }

    }


}