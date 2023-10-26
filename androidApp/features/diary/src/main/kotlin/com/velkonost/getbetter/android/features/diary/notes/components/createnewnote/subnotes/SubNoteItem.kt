package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.subnotes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.SingleLineTextField
import com.velkonost.getbetter.shared.features.diary.model.SubNoteUI

@Composable
fun SubNoteItem(
    modifier: Modifier = Modifier,
    item: SubNoteUI
) {
    SingleLineTextField(
        value = item.text,
        placeholderText = "",
        isEnabled = false,
        onValueChanged = {}
    )
}