package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.subnotes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.SingleLineTextField

@Composable
fun AddSubNoteItem(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit
) {
    SingleLineTextField(
        value = value,
        placeholderText = "placeholder",
        onValueChanged = onValueChanged
    )

}