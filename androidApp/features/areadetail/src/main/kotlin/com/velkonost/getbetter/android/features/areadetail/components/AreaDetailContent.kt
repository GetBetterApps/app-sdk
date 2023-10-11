package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.core.compose.components.SingleLineTextField
import com.velkonost.getbetter.core.compose.components.area.EmojiPicker
import com.velkonost.getbetter.core.compose.components.area.SelectedEmojiImage
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.features.areadetail.presentation.model.AreaDetailUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AreaDetailContent(
    modifier: Modifier = Modifier,
    areaData: AreaDetailUI,
    isEditing: Boolean,
    isEmojiPickerVisible: MutableState<Boolean>,
    onEmojiClick: (Emoji) -> Unit,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row {
            Spacer(modifier = modifier.weight(1f))
            SelectedEmojiImage(
                selectedEmoji = areaData.emoji,
                imageSize = 96
            ) {
                if (isEditing) {
                    isEmojiPickerVisible.value = !isEmojiPickerVisible.value
                }
            }
            Spacer(modifier = modifier.weight(1f))
        }

        EmojiPicker(
            isVisible = isEmojiPickerVisible.value,
            items = Emoji.values().toList(),
            onEmojiClick = onEmojiClick
        )

        Row(modifier = modifier.padding(top = 24.dp)) {
            Spacer(modifier = modifier.weight(1f))
            SingleLineTextField(
                value = areaData.name,
                placeholderText = stringResource(resource = SharedR.strings.diary_areas_create_new_name_hint),
                isEnabled = isEditing,
                textAlign = TextAlign.Center,
                textStyle = MaterialTheme.typography.titleLarge,
                paddingValues = PaddingValues(),
                onValueChanged = onNameChanged
            )
            Spacer(modifier = modifier.weight(1f))
        }

        Row {
            Spacer(modifier = modifier.weight(1f))
            MultilineTextField(
                value = areaData.description,
                minLines = 1,
                placeholderText = stringResource(resource = SharedR.strings.diary_areas_create_new_description_hint),
                textAlign = TextAlign.Center,
                isEnabled = isEditing,
                paddingValues = PaddingValues(top = 4.dp),
                onValueChanged = onDescriptionChanged
            )
            Spacer(modifier = modifier.weight(1f))
        }

        AnimatedVisibility(visible = !isEditing) {
            AreaDataContent()
        }
    }
}

@Composable
internal fun AreaDataContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .weight(1f)
                .padding(end = 4.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
        ) {
            Text(
                modifier = modifier.align(Alignment.Center),
                text = "14000\nmembers",
                color = colorResource(resource = SharedR.colors.text_secondary),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = modifier
                .weight(1f)
                .padding(start = 4.dp, end = 4.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = "20200\nnotes",
                color = colorResource(resource = SharedR.colors.text_secondary),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = modifier
                .weight(1f)
                .padding(start = 4.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = "20200\ntasks",
                color = colorResource(resource = SharedR.colors.text_secondary),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}