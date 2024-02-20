package com.velkonost.getbetter.core.compose.components.note.tags

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_12
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_6
import com.velkonost.getbetter.core.compose.theme.Pixel.PX_ZERO
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsBlock(
    modifier: Modifier = Modifier,
    tags: List<TagUI>,
    newTag: TagUI,
    onlyView: Boolean = false,
    onNewTagChanged: (String) -> Unit,
    onAddNewTag: () -> Unit,
    onTagDelete: (String) -> Unit
) {

    val textWidth = remember { 0.8f }
    val viewPadding = remember { DP_16 }
    val viewMargin = remember { PX_ZERO }
    val listTopPadding = remember { DP_12 }
    val listItemPadding = remember { DP_6 }

    PrimaryBox(padding = viewMargin) {
        Column(modifier = modifier.padding(viewPadding)) {
            Text(
                modifier = modifier
                    .fillMaxWidth(textWidth),
                text = stringResource(resource = SharedR.strings.create_note_tags_title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(resource = SharedR.colors.text_primary),
                style = MaterialTheme.typography.titleMedium
            )

            FlowRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = listTopPadding),
                horizontalArrangement = Arrangement.spacedBy(listItemPadding),
                verticalArrangement = Arrangement.spacedBy(listItemPadding)
            ) {

                tags.forEach { tag ->
                    TagItem(
                        tag = tag,
                        onlyView = onlyView,
                        onTagDelete = onTagDelete
                    )
                }

                AnimatedVisibility(visible = !onlyView) {
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


}