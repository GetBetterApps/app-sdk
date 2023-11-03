package com.velkonost.getbetter.android.features.notedetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.android.features.notedetail.components.ActionButtons
import com.velkonost.getbetter.android.features.notedetail.components.NoteDetailHeader
import com.velkonost.getbetter.core.compose.components.AppAlertDialog
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.components.note.CompletionDateBlock
import com.velkonost.getbetter.core.compose.components.note.subnotes.SubNotesBlock
import com.velkonost.getbetter.core.compose.components.note.tags.TagsBlock
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.features.notedetail.presentation.NoteDetailViewModel
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailAction
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailEvent
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.State
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val isSubNotesBlockVisible = remember { mutableStateOf(true) }


    val confirmDeleteNoteDialog = remember { mutableStateOf(false) }
    val areaDetailSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val selectedAreaId = remember { mutableStateOf<Int?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
                    .fillMaxSize()
            ) {
                item {
                    NoteDetailHeader {
                        viewModel.dispatch(NavigateBack)
                    }
                }

                item {
                    PrimaryBox(padding = 0) {
                        Row(
                            modifier = modifier
                                .padding(16.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) {
                                    scope.launch {
                                        selectedAreaId.value = state.area!!.id
                                        areaDetailSheetState.show()
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                modifier = modifier.size(32.dp),
                                painter = painterResource(imageResource = Emoji.getIconById(state.area!!.emojiId!!)),
                                contentDescription = null
                            )

                            Text(
                                modifier = modifier
                                    .padding(start = 12.dp)
                                    .fillMaxWidth(0.8f),
                                text = state.area!!.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = colorResource(resource = SharedR.colors.text_primary),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }

                item {
                    MultilineTextField(
                        value = state.noteText,
                        placeholderText = stringResource(
                            resource =
                            if (state.noteType == NoteType.Default) SharedR.strings.create_note_text_hint
                            else SharedR.strings.create_goal_text_hint
                        ),
                        isEnabled = state.noteState == State.Editing,
                        onValueChanged = {
                            viewModel.dispatch(NoteDetailAction.TextChanged(it))
                        }
                    )
                }

                item {
                    AnimatedVisibility(visible = state.noteType == NoteType.Goal) {
                        CompletionDateBlock(
                            enabled = state.noteState == State.Editing,
                            initialValue = state.expectedCompletionDate,
                            initialValueStr = state.expectedCompletionDateStr,
                            onSetCompletionDate = {
                                viewModel.dispatch(NoteDetailAction.SetCompletionDate(it))
                            }
                        )
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = state.tags.isNotEmpty() || state.noteState == State.Editing
                    ) {
                        TagsBlock(
                            tags = state.tags,
                            newTag = state.newTag,
                            onlyView = state.noteState == State.View,
                            onNewTagChanged = {
                                viewModel.dispatch(NoteDetailAction.NewTagTextChanged(it))
                            },
                            onAddNewTag = {
                                viewModel.dispatch(NoteDetailAction.AddNewTag)
                            },
                            onTagDelete = {
                                viewModel.dispatch(NoteDetailAction.RemoveTag(it))
                            }
                        )
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = state.noteType == NoteType.Goal
                                && (state.subNotes.isNotEmpty() || state.noteState == State.Editing)
                    ) {
                        SubNotesBlock(
                            items = state.subNotes,
                            newSubNote = state.newSubNote,
                            onlyView = state.noteState == State.View,
                            isSubNotesBlockVisible = isSubNotesBlockVisible,
                            onAddNewSubNote = {
                                viewModel.dispatch(NoteDetailAction.AddSubNote)
                            },
                            onNewSubNoteChanged = {
                                viewModel.dispatch(NoteDetailAction.NewSubNoteTextChanged(it))
                            },
                            onSubNoteDelete = {
                                viewModel.dispatch(NoteDetailAction.RemoveSubNote(it))
                            }
                        )
                    }
                }

                item {
                    AnimatedVisibility(
                        modifier = modifier.padding(top = 12.dp),
                        visible = state.allowEdit
                    ) {
                        ActionButtons(
                            noteState = state.noteState,
                            onEditClick = {
                                viewModel.dispatch(NoteDetailAction.StartEditClick)
                            },
                            onDeleteClick = {
                                confirmDeleteNoteDialog.value = true

                            },
                            onSaveClick = {
                                viewModel.dispatch(NoteDetailAction.EndEditClick)
                            },
                            onCancelSaveClick = {
                                viewModel.dispatch(NoteDetailAction.CancelEditClick)
                            }
                        )
                    }
                }
            }


        }
    }

    AreaDetailScreen(
        modalSheetState = areaDetailSheetState,
        areaId = selectedAreaId.value,
        onAreaChanged = {
            viewModel.dispatch(NoteDetailAction.AreaChanged)
        }
    )

    if (confirmDeleteNoteDialog.value) {
        AppAlertDialog(
            title = stringResource(resource = SharedR.strings.add_area_confirm_delete_title),
            text = stringResource(resource = SharedR.strings.add_area_confirm_delete_text),
            confirmTitle = stringResource(resource = SharedR.strings.confirm),
            cancelTitle = stringResource(resource = SharedR.strings.cancel),
            onDismiss = { confirmDeleteNoteDialog.value = false },
            onConfirmClick = {
                viewModel.dispatch(NoteDetailAction.DeleteClick)
                confirmDeleteNoteDialog.value = false
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest {
            when (it) {
                is NoteDetailEvent.DeleteSuccess -> {
                    viewModel.dispatch(NavigateBack)
                }

                is NoteDetailEvent.EditSuccess -> {
                    //TODO
                }
            }
        }
    }

}