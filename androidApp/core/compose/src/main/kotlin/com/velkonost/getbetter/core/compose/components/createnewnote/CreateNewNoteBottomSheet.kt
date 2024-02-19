package com.velkonost.getbetter.core.compose.components.createnewnote

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
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
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.HintButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.components.note.areapicker.AreaPicker
import com.velkonost.getbetter.core.compose.components.note.completiondate.CompletionDateBlock
import com.velkonost.getbetter.core.compose.components.note.subnotes.SubNotesBlock
import com.velkonost.getbetter.core.compose.components.note.tags.TagsBlock
import com.velkonost.getbetter.core.compose.components.note.taskpicker.TaskPicker
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_12
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_20
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_30
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_40
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_50
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_70
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_8
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)
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
    onCreateClick: () -> Unit,
    onHintClick: () -> Unit
) {

    val sheetCorners = remember { DP_12 }
    val viewPaddingBottom = remember { DP_40 }
    val loadingViewPadding = remember { DP_20 }

    val sheetHeight = remember { 0.9f }
    val gradientAlpha = remember { 0.7f }
    val gradientHeight = remember { DP_30 }

    val contentPadding = remember { DP_20 }
    val hintLeadingPadding = remember { DP_8 }
    val buttonBottomPadding = remember { DP_70 }
    val contentBottomPadding = remember { DP_50 }

    val isAreaPickerVisible = remember { mutableStateOf(false) }
    val isTaskPickerVisible = remember { mutableStateOf(false) }
    val isSubNotesBlockVisible = remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    val areasPagerState =
        rememberPagerState(initialPage = 0, pageCount = { state.availableAreas.size })
    val tasksPagerState =
        rememberPagerState(initialPage = 0, pageCount = { state.availableTasks.size })

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = sheetCorners, topEnd = sheetCorners),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            if (state.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(sheetHeight)
                        .padding(loadingViewPadding)
                ) {
                    Loader(modifier = modifier.align(Alignment.Center))
                }
            } else {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(sheetHeight)
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(sheetHeight)
                            .padding(bottom = viewPaddingBottom)
                            .verticalScroll(scrollState)
                    ) {
                        Column(
                            modifier = modifier
                                .padding(contentPadding)
                                .padding(bottom = contentBottomPadding)
                        ) {

                            Row(
                                modifier = modifier.align(Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(
                                        resource =
                                        if (state.type == NoteType.Default) SharedR.strings.create_note_title
                                        else SharedR.strings.create_goal_title
                                    ),
                                    color = colorResource(resource = SharedR.colors.text_title),
                                    style = MaterialTheme.typography.headlineSmall
                                )

                                HintButton(
                                    modifier = modifier.padding(start = hintLeadingPadding),
                                    onClick = onHintClick
                                )
                            }

                            AreaPicker(
                                areas = state.availableAreas,
                                areasPagerState = areasPagerState,
                                selectedArea = state.selectedArea,
                                isAreaPickerVisible = isAreaPickerVisible,
                                onAreaSelect = onAreaSelect,
                                modalSheetState = modalSheetState,
                                noteType = state.type
                            )

                            TaskPicker(
                                tasks = state.availableTasks,
                                forceSelectedTask = state.forceSelectedTask,
                                selectedTask = state.selectedTask,
                                tasksPagerState = tasksPagerState,
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
                                CompletionDateBlock(onSetCompletionDate = onSetCompletionDate)
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
                        WeightedSpacer()

                        Box(
                            modifier = modifier
                                .height(gradientHeight)
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            colorResource(resource = SharedR.colors.main_background)
                                                .copy(alpha = gradientAlpha),
                                            colorResource(resource = SharedR.colors.main_background),
                                        ),
                                    ),
                                )
                        )

                        AppButton(
                            modifier = modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = buttonBottomPadding),
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
    ) {}

    LaunchedEffect(modalSheetState.currentValue) {
        isAreaPickerVisible.value = false
        isSubNotesBlockVisible.value = false
    }

    LaunchedEffect(state.forceSelectedArea) {
        state.forceSelectedArea?.let { area ->
            val index = state.availableAreas.indexOfFirst { it.id == area.id }
            if (index != -1) {
                areasPagerState.scrollToPage(index)
            }
        }
    }

    LaunchedEffect(key1 = state.forceSelectedTask) {
        state.forceSelectedTask?.let { task ->
            val taskIndex = state.availableTasks.indexOfFirst { it?.id == task.id }
            if (taskIndex != -1) {
                tasksPagerState.scrollToPage(taskIndex)
                isTaskPickerVisible.value = true
            }
        }
    }

}