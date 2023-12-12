package com.velkonost.getbetter.android.features.taskdetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.android.features.taskdetail.components.AbilityData
import com.velkonost.getbetter.android.features.taskdetail.components.AbilityDataHidden
import com.velkonost.getbetter.android.features.taskdetail.components.TaskDetailHeader
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.createnewnote.CreateNewNoteBottomSheet
import com.velkonost.getbetter.core.compose.components.details.AreaData
import com.velkonost.getbetter.core.compose.components.notelist.AddNoteItem
import com.velkonost.getbetter.core.compose.components.notelist.NoteItem
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.taskdetail.presentation.TaskDetailViewModel
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailAction
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailEvent
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val interactionSource = remember { MutableInteractionSource() }
    val listState = rememberLazyListState()

    val selectedAreaId = remember { mutableStateOf<Int?>(null) }
    val areaDetailSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val createNewNoteSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading || state.task == null) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxSize(),
                state = listState,
                contentPadding = PaddingValues(bottom = 220.dp)
            ) {
                item {
                    TaskDetailHeader(
                        isShortInfo = state.task!!.isShortInfo,
                        isFavorite = state.task!!.isFavorite,
                        isFavoriteLoading = state.task!!.isFavoriteLoading,
                        onBackClick = {
                            viewModel.dispatch(TaskDetailAction.NavigateBack)
                        },
                        onFavoriteClick = {
                            viewModel.dispatch(TaskDetailAction.FavoriteClick)
                        }
                    )
                }

                item {
                    state.area?.let {
                        AreaData(area = it) {
                            scope.launch {
                                selectedAreaId.value = it.id
                                areaDetailSheetState.show()
                            }
                        }
                    }
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 24.dp),
                        text = state.task!!.name,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                        color = colorResource(resource = SharedR.colors.text_light),
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = stringResource(resource = SharedR.strings.task_what_to_do_title),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 6.dp),
                        text = state.task!!.whatToDo,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = stringResource(resource = SharedR.strings.task_why_title),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 6.dp),
                        text = state.task!!.why,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = stringResource(resource = SharedR.strings.task_abilities_title),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                }

                if (!state.task!!.isShortInfo) {
                    items(state.task!!.abilities, key = { it.id!! }) { ability ->
                        AbilityData(
                            item = ability,
                            onClick = {

                            }
                        )
                    }

                    item {
                        Row {
                            Spacer(modifier.weight(1f))
                            Text(
                                modifier = modifier.padding(top = 24.dp),
                                text = stringResource(resource = SharedR.strings.task_mask_as).uppercase(),
                                style = MaterialTheme.typography.labelMedium,
                                color = colorResource(resource = SharedR.colors.text_primary)
                            )
                            Spacer(modifier.weight(1f))
                        }
                    }

                    item {
                        Row(modifier = modifier.padding(top = 12.dp)) {
                            Spacer(modifier.weight(1f))
                            Spacer(modifier.weight(1f))

                            Text(
                                modifier = modifier
                                    .padding(end = 6.dp)
                                    .shadow(
                                        elevation = (if (state.task!!.isNotInteresting) 8 else 0).dp,
                                        shape = MaterialTheme.shapes.medium,
                                    )
                                    .background(
                                        color = colorResource(
                                            resource = if (state.task!!.isNotInteresting) SharedR.colors.button_gradient_start
                                            else SharedR.colors.background_item
                                        ),
                                        shape = MaterialTheme.shapes.medium
                                    )
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) { viewModel.dispatch(TaskDetailAction.NotInterestingClick) },
                                text = stringResource(resource = SharedR.strings.task_not_interesting_title),
                                style = MaterialTheme.typography.labelLarge,
                                color = colorResource(
                                    resource = if (state.task!!.isNotInteresting) SharedR.colors.text_light
                                    else SharedR.colors.text_primary
                                )
                            )
                            Text(
                                modifier = modifier
                                    .padding(start = 6.dp)
                                    .shadow(
                                        elevation = (if (state.task!!.isCompleted) 8 else 0).dp,
                                        shape = MaterialTheme.shapes.medium,
                                    )
                                    .background(
                                        color = colorResource(
                                            resource = if (state.task!!.isCompleted) SharedR.colors.button_gradient_start
                                            else SharedR.colors.background_item
                                        ),
                                        shape = MaterialTheme.shapes.medium
                                    )
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) { viewModel.dispatch(TaskDetailAction.CompletedClick) },
                                text = stringResource(resource = SharedR.strings.task_completed_title),
                                style = MaterialTheme.typography.labelLarge,
                                color = colorResource(
                                    resource = if (state.task!!.isCompleted) SharedR.colors.text_light
                                    else SharedR.colors.text_primary
                                )
                            )

                            Spacer(modifier.weight(1f))
                            Spacer(modifier.weight(1f))
                        }
                    }
                } else {
                    item {
                        AbilityDataHidden()
                    }
                }

                item {
                    AnimatedVisibility(visible = state.userNotesViewState.items.isNotEmpty()) {
                        androidx.compose.material3.Text(
                            modifier = modifier
                                .padding(top = 24.dp)
                                .fillMaxWidth(0.8f),
                            text = stringResource(resource = SharedR.strings.task_user_notes_title),
                            color = colorResource(resource = SharedR.colors.text_primary),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }

                if (state.userNotesViewState.items.isEmpty() && state.userNotesViewState.isLoading) {
                    item {
                        Loader(modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center))
                    }
                }

                items(state.userNotesViewState.items, key = { it.id }) {
                    NoteItem(
                        item = it,
                        horizontalPadding = 0,
                        onClick = {

                        },
                        onLikeClick = {

                        }
                    )
                }
            }

            if (!state.task!!.isShortInfo) {
                AddNoteItem(
                    paddingBottom = 70,
                    createGoalClick = {
                        scope.launch {
                            if (state.createNewNoteViewState.availableAreas.isEmpty()) {
                                viewModel.dispatch(CreateNewNoteAction.CloseBecauseZeroAreas)
                            } else {
                                viewModel.dispatch(TaskDetailAction.CreateGoalClick)
                                createNewNoteSheetState.show()
                            }
                        }
                    },
                    createNoteClick = {
                        scope.launch {
                            if (state.createNewNoteViewState.availableAreas.isEmpty()) {
                                viewModel.dispatch(CreateNewNoteAction.CloseBecauseZeroAreas)
                            } else {
                                viewModel.dispatch(TaskDetailAction.CreateNoteClick)
                                createNewNoteSheetState.show()
                            }
                        }
                    }
                )
            }
        }
    }

    listState.OnBottomReached(
        buffer = state.userNotesViewState.loadMorePrefetch,
        isLoading = state.userNotesViewState.isLoading
    ) {
        viewModel.dispatch(TaskDetailAction.UserNotesLoadNextPage)
    }

    AreaDetailScreen(
        modalSheetState = areaDetailSheetState,
        areaId = selectedAreaId.value,
        onAreaChanged = {
            viewModel.dispatch(TaskDetailAction.AreaChanged)
        }
    )

    CreateNewNoteBottomSheet(
        state = state.createNewNoteViewState,
        modalSheetState = createNewNoteSheetState,
        onAreaSelect = {
            viewModel.dispatch(CreateNewNoteAction.AreaSelect(it))
        },
        onTaskSelect = {
            viewModel.dispatch(CreateNewNoteAction.TaskSelect(it))
        },
        onTextChanged = {
            viewModel.dispatch(CreateNewNoteAction.TextChanged(it))
        },
        onPrivateChanged = {
            viewModel.dispatch(CreateNewNoteAction.PrivateChanged)
        },
        onNewTagChanged = {
            viewModel.dispatch(CreateNewNoteAction.NewTagTextChanged(it))
        },
        onAddNewTag = {
            viewModel.dispatch(CreateNewNoteAction.AddNewTag)
        },
        onTagDelete = {
            viewModel.dispatch(CreateNewNoteAction.RemoveTag(it))
        },
        onNewSubNoteChanged = {
            viewModel.dispatch(CreateNewNoteAction.NewSubNoteTextChanged(it))
        },
        onAddNewSubNote = {
            viewModel.dispatch(CreateNewNoteAction.AddSubNote)
        },
        onSubNoteDelete = {
            viewModel.dispatch(CreateNewNoteAction.RemoveSubNote(it))
        },
        onSetCompletionDate = {
            viewModel.dispatch(CreateNewNoteAction.SetCompletionDate(it))
        },
        onCreateClick = {
            viewModel.dispatch(CreateNewNoteAction.CreateClick)
        }
    )

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest {
            when (it) {
                is TaskDetailEvent.NewNoteCreatedSuccess -> {
                    createNewNoteSheetState.hide()
                }
            }
        }
    }

}