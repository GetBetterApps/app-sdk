package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.features.diary.model.TagUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsBlock(
    modifier: Modifier = Modifier,
    tags: List<TagUI>,
    newTag: TagUI,
    onNewTagChanged: (String) -> Unit,
    onAddNewTag: () -> Unit,
    onTagDelete: (String) -> Unit
) {

    PrimaryBox(padding = 0) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth(0.8f),
                text = stringResource(resource = SharedR.strings.create_note_tags_title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(resource = SharedR.colors.text_primary),
                style = MaterialTheme.typography.titleMedium
            )

            FlowRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
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
                    value = newTag,
                    placeholderText = stringResource(resource = SharedR.strings.create_note_tags_hint),
                    onValueChanged = onNewTagChanged,
                    onAddNewTag = onAddNewTag
                )
            }
        }
    }


}