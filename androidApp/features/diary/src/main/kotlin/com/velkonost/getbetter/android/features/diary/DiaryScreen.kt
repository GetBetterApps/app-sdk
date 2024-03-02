package com.velkonost.getbetter.android.features.diary

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.android.features.diary.areas.AreasView
import com.velkonost.getbetter.android.features.diary.areas.components.createnewarea.CreateNewAreaBottomSheet
import com.velkonost.getbetter.android.features.diary.notes.NotesView
import com.velkonost.getbetter.android.features.diary.tasks.TasksView
import com.velkonost.getbetter.core.compose.components.HintButton
import com.velkonost.getbetter.core.compose.components.HintSubscriptionSheet
import com.velkonost.getbetter.core.compose.components.PrimaryTabs
import com.velkonost.getbetter.core.compose.components.createnewnote.CreateNewNoteBottomSheet
import com.velkonost.getbetter.core.compose.composable.OnLifecycleEvent
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.diary.presentation.DiaryViewModel
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.AddAreaClick
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.AreaLikeClick
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.AreasViewState
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.CreateNewAreaEvent
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.DiaryAction
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.DiaryEvent
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.NoteClick
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.NoteLikeClick
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.NotesViewState
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.TaskClick
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.TasksViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel,
    forceHideBottomBar: MutableState<Boolean> = mutableStateOf(false)
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.tabs.size })

    val scope = rememberCoroutineScope()
    val createNewAreaSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    val createNewNoteSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    val areaDetailSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val hintSubscriptionSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Expanded,
        skipHalfExpanded = true
    )

    val selectedAreaId = remember { mutableStateOf<Int?>(null) }

    val hintSubscriptionText = remember { mutableStateOf("") }

    val areasLimitText = stringResource(resource = SharedR.strings.hint_subscription_areas)
    val tasksUpdateLimitText = stringResource(resource = SharedR.strings.hint_subscription_tasks)

    BackHandler {
        scope.launch {
            if (createNewAreaSheetState.currentValue == ModalBottomSheetValue.Expanded) {
                createNewAreaSheetState.hide()
            }

            if (createNewNoteSheetState.currentValue == ModalBottomSheetValue.Expanded) {
                createNewNoteSheetState.hide()
            }

            if (areaDetailSheetState.currentValue == ModalBottomSheetValue.Expanded) {
                areaDetailSheetState.hide()
            }
        }
    }

    Box {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PrimaryTabs(
                    tabs = state.tabs.map { it.title.toString(LocalContext.current) },
                    pagerState = pagerState,
                    widthFraction = 0.9f
                )
                HintButton(modifier = Modifier.padding(top = 40.dp)) {
                    viewModel.dispatch(DiaryAction.HintClick(index = pagerState.currentPage))
                }
            }

            DiaryScreenContent(
                pagerState = pagerState,
                notesState = state.notesViewState,
                areasState = state.areasViewState,
                tasksState = state.tasksViewState,
                adPosition = state.adPosition,
                adSlotId = state.adId,
                noteClick = {
                    viewModel.dispatch(NoteClick(it))
                },
                noteLikeClick = {
                    viewModel.dispatch(NoteLikeClick(it))
                },
                areaClick = {
                    scope.launch {
                        selectedAreaId.value = it
                        areaDetailSheetState.show()
                    }
                },
                areaLikeClick = {
                    viewModel.dispatch(AreaLikeClick(it))
                },
                createNewAreaClick = {
                    scope.launch {
                        if (state.areasViewState.canCreateNewArea) {
                            viewModel.dispatch(CreateNewAreaAction.Open)
                            createNewAreaSheetState.show()
                        } else {
                            hintSubscriptionText.value = areasLimitText
                            hintSubscriptionSheetState.show()
                        }
                    }
                },
                addExistingAreaClick = {
                    viewModel.dispatch(AddAreaClick)
                },
                createGoalClick = {
                    scope.launch {
                        if (state.createNewNoteViewState.availableAreas.isEmpty()) {
                            viewModel.dispatch(CreateNewNoteAction.CloseBecauseZeroAreas)
                        } else {
                            viewModel.dispatch(CreateNewNoteAction.OpenGoal)
                            createNewNoteSheetState.show()
                        }
                    }
                },
                createNoteClick = {
                    scope.launch {
                        if (state.createNewNoteViewState.availableAreas.isEmpty()) {
                            viewModel.dispatch(CreateNewNoteAction.CloseBecauseZeroAreas)
                        } else {
                            viewModel.dispatch(CreateNewNoteAction.OpenDefault)
                            createNewNoteSheetState.show()
                        }
                    }
                },
                notesLoadNextPage = {
                    viewModel.dispatch(DiaryAction.NotesLoadNextPage)
                },
                taskClick = {
                    viewModel.dispatch(TaskClick(it))
                },
                tasksListUpdateClick = {
                    if (state.tasksViewState.canUpdateList) {
                        viewModel.dispatch(DiaryAction.TasksListUpdateClick)
                    } else {
                        scope.launch {
                            hintSubscriptionText.value = tasksUpdateLimitText
                            hintSubscriptionSheetState.show()
                        }
                    }
                },
                taskFavoriteClick = {
                    viewModel.dispatch(DiaryAction.TaskFavoriteClick(it))
                }
            )
        }

        CreateNewAreaBottomSheet(
            modalSheetState = createNewAreaSheetState,
            isLoading = state.createNewAreaViewState.isLoading,
            emojiItems = state.emojiList,
            state = state.createNewAreaViewState,
            onEmojiClick = {
                viewModel.dispatch(CreateNewAreaAction.EmojiSelect(it))
            },
            onNameChanged = {
                viewModel.dispatch(CreateNewAreaAction.NameChanged(it))
            },
            onDescriptionChanged = {
                viewModel.dispatch(CreateNewAreaAction.DescriptionChanged(it))
            },
            onRequiredLevelChanged = {
                viewModel.dispatch(CreateNewAreaAction.RequiredLevelChanged(it))
            },
            onPrivateChanged = {
                viewModel.dispatch(CreateNewAreaAction.PrivateChanged)
            },
            onCreateClick = {
                viewModel.dispatch(CreateNewAreaAction.CreateClick)
            },
            onHintClick = {
                viewModel.dispatch(CreateNewAreaAction.HintClick)
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
            },
            onHintClick = {
                viewModel.dispatch(CreateNewNoteAction.HintClick)
            }
        )

        AreaDetailScreen(
            modalSheetState = areaDetailSheetState,
            areaId = selectedAreaId.value,
            onAreaChanged = { viewModel.refreshData() }
        )

        HintSubscriptionSheet(
            modalSheetState = hintSubscriptionSheetState,
            text = hintSubscriptionText.value,
            onClick = {
                viewModel.dispatch(DiaryAction.NavigateToPaywallClick)
            }
        )

    }

    LaunchedEffect(Unit) {
        combine(
            snapshotFlow { createNewAreaSheetState.currentValue },
            snapshotFlow { areaDetailSheetState.currentValue },
            snapshotFlow { createNewNoteSheetState.currentValue },
            snapshotFlow { hintSubscriptionSheetState.currentValue },
        ) { createNewAreaState, areaDetailState, createNewNoteState, hintSubscriptionState ->
            val hideBottomBar =
                createNewAreaState != ModalBottomSheetValue.Hidden
                        || areaDetailState != ModalBottomSheetValue.Hidden
                        || createNewNoteState != ModalBottomSheetValue.Hidden
                        || hintSubscriptionState != ModalBottomSheetValue.Hidden
            hideBottomBar
        }.collect {
            forceHideBottomBar.value = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest {
            when (it) {
                is CreateNewAreaEvent.CreatedSuccess -> {
                    createNewAreaSheetState.hide()
                    viewModel.refreshData()
                }

                is DiaryEvent.NewNoteCreatedSuccess -> {
                    createNewNoteSheetState.hide()
                    viewModel.refreshData()
                }
            }
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.dispatch(DiaryAction.HintClick(firstTime = true, index = page))
        }
    }

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.refreshData()
            }

            else -> {}
        }
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryScreenContent(
    pagerState: PagerState,
    notesState: NotesViewState,
    areasState: AreasViewState,
    tasksState: TasksViewState,
    adPosition: Int,
    adSlotId: String,
    noteClick: (Note) -> Unit,
    noteLikeClick: (Note) -> Unit,
    areaClick: (Int) -> Unit,
    areaLikeClick: (Area) -> Unit,
    createNewAreaClick: () -> Unit,
    addExistingAreaClick: () -> Unit,
    createGoalClick: () -> Unit,
    createNoteClick: () -> Unit,
    notesLoadNextPage: () -> Unit,
    taskClick: (TaskUI) -> Unit,
    tasksListUpdateClick: () -> Unit,
    taskFavoriteClick: (TaskUI) -> Unit
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        beyondBoundsPageCount = 2
    ) { index ->
        when (index) {
            0 -> NotesView(
                items = notesState.items,
                isLoading = notesState.isLoading,
                loadMorePrefetch = notesState.loadMorePrefetch,
                adPosition = adPosition,
                adSlotId = adSlotId,
                createGoalClick = createGoalClick,
                createNoteClick = createNoteClick,
                itemClick = noteClick,
                itemLikeClick = noteLikeClick,
                onBottomReach = notesLoadNextPage
            )

            1 -> AreasView(
                items = areasState.items,
                isLoading = areasState.isLoading,
                adPosition = adPosition,
                adSlotId = adSlotId,
                itemClick = areaClick,
                itemLikeClick = areaLikeClick,
                createNewAreaClick = createNewAreaClick,
                addExistingAreaClick = addExistingAreaClick,
            )

            else -> TasksView(
                isLoading = tasksState.isLoading,
                favoriteItems = tasksState.favoriteItems,
                currentItems = tasksState.currentItems,
                completedItems = tasksState.completedItems,
                adSlotId = adSlotId,
                onTaskClick = taskClick,
                onTaskListUpdateClick = tasksListUpdateClick,
                onTaskFavoriteClick = taskFavoriteClick
            )
        }
    }
}

