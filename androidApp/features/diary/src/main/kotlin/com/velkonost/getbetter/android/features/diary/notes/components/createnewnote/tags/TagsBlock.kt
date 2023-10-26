package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsBlock(
    modifier: Modifier = Modifier,
    tags: List<String>,
    newTagText: String,
    onNewTagChanged: (String) -> Unit,
    onAddNewTag: () -> Unit,
    onTagDelete: (String) -> Unit
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        tags.forEach { tag ->
            TagItem(
                tag = tag,
                onTagDelete = onTagDelete
            )
        }

        NewTagField(
            value = newTagText,
            placeholderText = "Enter tag",
            onValueChanged = onNewTagChanged,
            onAddNewTag = onAddNewTag
        )
    }
}