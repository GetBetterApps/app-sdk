package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.subnotes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

            AnimatedVisibility(visible = isSubNotesBlockVisible.value) {
                AnimatedContent(targetState = items, label = "") { subNotes ->
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        items(subNotes, key = { it.id }) { item ->
                            SubNoteItem(
                                item = item,
                                onDeleteSubNote = onSubNoteDelete
                            )
                        }

                        item {
                            AddSubNoteItem(
                                value = newSubNote.text,
                                placeholderText = "",
                                onValueChanged = onNewSubNoteChanged,
                                onAddSubNote = onAddNewSubNote
                            )
                        }
                    }
                }

            }
        }
    }

}