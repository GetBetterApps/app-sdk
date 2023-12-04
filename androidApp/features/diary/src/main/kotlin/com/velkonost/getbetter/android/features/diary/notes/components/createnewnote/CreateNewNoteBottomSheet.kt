package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.core.compose.components.note.areapicker.AreaPicker
import com.velkonost.getbetter.core.compose.components.note.completiondate.CompletionDateBlock
import com.velkonost.getbetter.core.compose.components.note.subnotes.SubNotesBlock
import com.velkonost.getbetter.core.compose.components.note.tags.TagsBlock
import com.velkonost.getbetter.core.compose.components.note.taskpicker.TaskPicker
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.features.calendars.contracts.CreateNewNoteViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun CreateNewNoteBottomSheet(
    modifier: Modifier = Modifier,
    state: CreateNewNoteViewState,
    modalSheetState: ModalBottomSheetState,
    onAreaSelect: (Area) -> Unit,
    onTaskSelect: (TaskUI?) -> Unit,
    onTextChanged: (String) -> Unit,
    onPrivateChanged: (Boolean) -> Unit,
    onNewTagChanged: (String) -> Unit,
    onAddNewTag: () -> Unit,
    onTagDelete: (String) -> Unit,
    onNewSubNoteChanged: (String) -> Unit,
    onAddNewSubNote: () -> Unit,
    onSubNoteDelete: (SubNoteUI) -> Unit,
    onSetCompletionDate: (Long?) -> Unit,
    onCreateClick: () -> Unit
) {

    val isAreaPickerVisible = remember { mutableStateOf(false) }
    val isTaskPickerVisible = remember { mutableStateOf(false) }
    val isSubNotesBlockVisible = remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            if (state.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(20.dp)
                ) {
                    Loader(modifier = modifier.align(Alignment.Center))
                }
            } else {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.9f)
                            .padding(bottom = 40.dp)
                            .verticalScroll(scrollState)
                    ) {
                        Column(
                            modifier = modifier
                                .padding(20.dp)
                                .padding(bottom = 50.dp)
                        ) {
                            Text(
                                modifier = modifier.align(Alignment.CenterHorizontally),
                                text = stringResource(
                                    resource =
                                    if (state.type == NoteType.Default) SharedR.strings.create_note_title
                                    else SharedR.strings.create_goal_title
                                ),
                                color = colorResource(resource = SharedR.colors.text_title),
                                style = MaterialTheme.typography.headlineSmall
                            )

                            AreaPicker(
                                areas = state.availableAreas,
                                selectedArea = state.selectedArea,
                                isAreaPickerVisible = isAreaPickerVisible,
                                onAreaSelect = onAreaSelect,
                                modalSheetState = modalSheetState,
                                noteType = state.type
                            )

                            TaskPicker(
                                tasks = state.availableTasks,
                                selectedTask = state.selectedTask,
                                isTaskPickerVisible = isTaskPickerVisible,
                                modalSheetState = modalSheetState,
                                onTaskSelect = onTaskSelect
                            )

                            MultilineTextField(
                                value = state.text,
                                placeholderText = stringResource(
                                    resource =
                                    if (state.type == NoteType.Default) SharedR.strings.create_note_text_hint
                                    else SharedR.strings.create_goal_text_hint
                                ),
                                onValueChanged = { onTextChanged.invoke(it) }
                            )

                            AnimatedVisibility(visible = state.type == NoteType.Goal) {
                                CompletionDateBlock(
                                    onSetCompletionDate = onSetCompletionDate
                                )
                            }

                            PrivateSwitch(
                                isPrivate = state.isPrivate,
                                isEnable = state.selectedArea != null && state.selectedArea?.isPrivate == false,
                                onCheckedChange = onPrivateChanged
                            )

                            TagsBlock(
                                tags = state.tags,
                                newTag = state.newTag,
                                onNewTagChanged = onNewTagChanged,
                                onAddNewTag = onAddNewTag,
                                onTagDelete = onTagDelete
                            )

                            AnimatedVisibility(visible = state.type == NoteType.Goal) {
                                SubNotesBlock(
                                    items = state.subNotes,
                                    newSubNote = state.newSubNote,
                                    isSubNotesBlockVisible = isSubNotesBlockVisible,
                                    onAddNewSubNote = onAddNewSubNote,
                                    onNewSubNoteChanged = onNewSubNoteChanged,
                                    onSubNoteDelete = onSubNoteDelete
                                )
                            }

                        }
                    }

                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = modifier.weight(1f))

                        Box(
                            modifier = modifier
                                .height(30.dp)
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            colorResource(resource = SharedR.colors.main_background)
                                                .copy(alpha = 0.7f),
                                            colorResource(resource = SharedR.colors.main_background),
                                        ),
                                    ),
                                )
                        )

                        AppButton(
                            modifier = modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 70.dp),
                            labelText = stringResource(
                                resource = SharedR.strings.diary_areas_create_button
                            ),
                            isLoading = false,
                            onClick = onCreateClick
                        )
                    }

                }
            }
        }
    ) {

    }

    LaunchedEffect(modalSheetState.currentValue) {
        isAreaPickerVisible.value = false
        isSubNotesBlockVisible.value = false
    }
}