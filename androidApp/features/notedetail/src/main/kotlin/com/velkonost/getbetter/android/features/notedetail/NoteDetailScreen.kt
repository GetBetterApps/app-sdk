package com.velkonost.getbetter.android.features.notedetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.android.features.notedetail.components.ActionButtons
import com.velkonost.getbetter.android.features.notedetail.components.AuthorData
import com.velkonost.getbetter.android.features.notedetail.components.NoteDetailHeader
import com.velkonost.getbetter.android.features.notedetail.components.comments.CommentItem
import com.velkonost.getbetter.android.features.notedetail.components.comments.NewCommentTextField
import com.velkonost.getbetter.android.features.profiledetail.ProfileDetailScreen
import com.velkonost.getbetter.core.compose.components.AppAlertDialog
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.core.compose.components.details.AreaData
import com.velkonost.getbetter.core.compose.components.details.TaskData
import com.velkonost.getbetter.core.compose.components.note.completiondate.CompletionDateBlock
import com.velkonost.getbetter.core.compose.components.note.subnotes.SubNotesBlock
import com.velkonost.getbetter.core.compose.components.note.tags.TagsBlock
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.features.notedetail.presentation.NoteDetailViewModel
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailAction
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailEvent
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
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
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isSubNotesBlockVisible = remember { mutableStateOf(true) }

    val confirmDeleteNoteDialog = remember { mutableStateOf(false) }
    val areaDetailSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val profileDetailSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val selectedAreaId = remember { mutableStateOf<Int?>(null) }
    val selectedUserId = remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 220.dp)
            ) {
                item {
                    NoteDetailHeader(
                        noteType = state.noteType,
                        isNotePrivate = state.isNotePrivate,
                        likesData = state.likesData,
                        onBackClick = {
                            viewModel.dispatch(NavigateBack)
                        },
                        onLikeClick = {
                            viewModel.dispatch(NoteDetailAction.LikeClick)
                        }
                    )
                }

                if (!state.allowEdit) {
                    item {
                        AuthorData(
                            isLoading = state.authorLoading,
                            author = state.author,
                            onClick = {
                                scope.launch {
                                    selectedUserId.value = state.author?.id
                                    profileDetailSheetState.show()
                                }
                            }
                        )
                    }
                }

                item {
                    state.area?.let {
                        AreaData(area = it) {
                            scope.launch {
                                selectedAreaId.value = state.area!!.id
                                areaDetailSheetState.show()
                            }
                        }
                    }
                }

                state.task?.let {
                    item {
                        TaskData(task = it) {
                            viewModel.dispatch(NoteDetailAction.TaskClick)
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
                        isEnabled = state.noteState == NoteState.Editing,
                        onValueChanged = {
                            viewModel.dispatch(NoteDetailAction.TextChanged(it))
                        }
                    )
                }

                item {
                    AnimatedVisibility(
                        visible = state.noteType == NoteType.Goal
                                && (state.allowEdit || state.expectedCompletionDate != null || state.completionDate != null)
                    ) {
                        CompletionDateBlock(
                            enabled = state.noteState == NoteState.Editing,
                            isLoading = state.isCompleteGoalLoading,
                            initialValue = state.expectedCompletionDate,
                            initialValueStr = state.expectedCompletionDateStr?.toString(context),
                            isCompleteVisible = state.allowEdit,
                            completionDateStr = state.completionDateStr?.toString(context),
                            onSetCompletionDate = {
                                viewModel.dispatch(NoteDetailAction.SetCompletionDate(it))
                            },
                            onCompleteClick = {
                                if (state.completionDate == null) {
                                    viewModel.dispatch(NoteDetailAction.CompleteClick)
                                } else {
                                    viewModel.dispatch(NoteDetailAction.UnCompleteClick)
                                }
                            }
                        )
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = state.tags.isNotEmpty() || state.noteState == NoteState.Editing
                    ) {
                        TagsBlock(
                            tags = state.tags,
                            newTag = state.newTag,
                            onlyView = state.noteState == NoteState.View,
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
                                && (state.subNotes.isNotEmpty() || state.noteState == NoteState.Editing)
                    ) {
                        SubNotesBlock(
                            items = state.subNotes,
                            newSubNote = state.newSubNote,
                            onlyView = state.noteState == NoteState.View,
                            isCompleteVisible = state.allowEdit,
                            isSubNotesBlockVisible = isSubNotesBlockVisible,
                            onAddNewSubNote = {
                                viewModel.dispatch(NoteDetailAction.AddSubNote)
                            },
                            onNewSubNoteChanged = {
                                viewModel.dispatch(NoteDetailAction.NewSubNoteTextChanged(it))
                            },
                            onSubNoteDelete = {
                                viewModel.dispatch(NoteDetailAction.RemoveSubNote(it))
                            },
                            onCompleteClick = {
                                if (it.completionDate == null) {
                                    viewModel.dispatch(NoteDetailAction.CompleteSubNoteClick(it))
                                } else {
                                    viewModel.dispatch(NoteDetailAction.UnCompleteSubNoteClick(it))
                                }
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

                item {
                    AnimatedVisibility(visible = state.commentsData.comments.isNotEmpty()) {
                        Text(
                            modifier = modifier
                                .padding(top = 24.dp)
                                .fillMaxWidth(0.8f),
                            text = stringResource(resource = SharedR.strings.note_detail_comments_title),
                            color = colorResource(resource = SharedR.colors.text_primary),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }

                state.commentsData.comments.forEach {
                    item {
                        CommentItem(
                            item = it,
                            onDeleteClick = {
                                viewModel.dispatch(NoteDetailAction.CommentRemoveClick(it))
                            }
                        )

                    }
                }
            }
        }
    }

    if (!state.isNotePrivate) {
        Column(modifier = modifier.fillMaxSize()) {
            Spacer(modifier.weight(1f))
            NewCommentTextField(
                value = state.commentsData.commentText,
                onValueChanged = {
                    viewModel.dispatch(NoteDetailAction.CommentTextChanged(it))
                },
                onSendClick = {
                    viewModel.dispatch(NoteDetailAction.CommentAddClick)
                }
            )
        }
    }

    AreaDetailScreen(
        modalSheetState = areaDetailSheetState,
        areaId = selectedAreaId.value,
        onAreaChanged = {
            viewModel.dispatch(NoteDetailAction.AreaChanged)
        }
    )

    ProfileDetailScreen(
        modalSheetState = profileDetailSheetState,
        userId = selectedUserId.value
    )

    if (confirmDeleteNoteDialog.value) {
        AppAlertDialog(
            title = stringResource(
                resource =
                if (state.noteType == NoteType.Default) SharedR.strings.note_detail_confirm_delete_title
                else SharedR.strings.goal_detail_confirm_delete_title
            ),
            text = stringResource(resource = SharedR.strings.note_detail_confirm_delete_text),
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