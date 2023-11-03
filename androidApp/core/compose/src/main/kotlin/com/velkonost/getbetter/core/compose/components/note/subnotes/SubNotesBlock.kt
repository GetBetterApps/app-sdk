package com.velkonost.getbetter.core.compose.components.note.subnotes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SubNotesBlock(
    modifier: Modifier = Modifier,
    onlyView: Boolean = false,
    newSubNote: SubNoteUI,
    items: List<SubNoteUI>,
    isCompleteVisible: Boolean = false,
    isSubNotesBlockVisible: MutableState<Boolean>,
    onNewSubNoteChanged: (String) -> Unit,
    onAddNewSubNote: () -> Unit,
    onSubNoteDelete: (SubNoteUI) -> Unit,
    onCompleteClick: ((SubNoteUI) -> Unit)? = null
) {
    PrimaryBox(padding = 0) {
        Column {
            SubNotesHeader(
                isSubNotesBlockVisible = isSubNotesBlockVisible
            )

            AnimatedVisibility(visible = isSubNotesBlockVisible.value) {
                AnimatedContent(targetState = items, label = "") { subNotes ->
                    Column(
                        modifier = modifier.padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        subNotes.forEach { item ->
                            SubNoteItem(
                                item = item,
                                onlyView = onlyView,
                                isCompleteVisible = isCompleteVisible,
                                onDeleteSubNote = onSubNoteDelete,
                                onCompleteClick = onCompleteClick
                            )
                        }

                        AnimatedVisibility(visible = !onlyView) {
                            AddSubNoteItem(
                                value = newSubNote.text,
                                placeholderText = stringResource(resource = SharedR.strings.create_note_subnote_hint),
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