package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.subnotes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.features.diary.model.SubNoteUI

@Composable
fun SubNotesBlock(
    modifier: Modifier = Modifier,
    items: List<SubNoteUI>,
    newSubNote: SubNoteUI,
    isSubNotesBlockVisible: MutableState<Boolean>,
    onNewSubNoteChanged: (String) -> Unit,
    onAddNewSubNote: () -> Unit,
    onSubNoteDelete: (SubNoteUI) -> Unit
) {
    PrimaryBox(padding = 0) {
        Column {
            SubNotesHeader(
                isSubNotesBlockVisible = isSubNotesBlockVisible
            )

            LazyColumn {
                items(items, key = { it.id }) { item ->
                    SubNoteItem(item = item)
                }

                item {
                    AddSubNoteItem(
                        value = newSubNote.text,
                        onValueChanged = onNewSubNoteChanged
                    )
                }
            }
        }
    }

}