package com.velkonost.getbetter.android.features.diary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.android.features.diary.areas.AreasView
import com.velkonost.getbetter.android.features.diary.areas.components.createnewarea.CreateNewAreaBottomSheet
import com.velkonost.getbetter.android.features.diary.notes.NotesView
import com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.CreateNewNoteBottomSheet
import com.velkonost.getbetter.android.features.diary.tasks.TasksView
import com.velkonost.getbetter.core.compose.components.PrimaryTabs
import com.velkonost.getbetter.core.compose.composable.OnLifecycleEvent
import com.velkonost.getbetter.shared.features.diary.DiaryViewModel
import com.velkonost.getbetter.shared.features.diary.contracts.AddAreaClick
import com.velkonost.getbetter.shared.features.diary.contracts.AreasViewState
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaEvent
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteEvent
import com.velkonost.getbetter.shared.features.diary.contracts.NotesViewState
import com.velkonost.getbetter.shared.features.diary.contracts.TasksViewState
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

    val selectedAreaId = remember { mutableStateOf<Int?>(null) }

    Box {
        Column {
            PrimaryTabs(
                tabs = state.tabs.map { it.title.toString(LocalContext.current) },
                pagerState = pagerState
            )

            DiaryScreenContent(
                pagerState = pagerState,
                notesState = state.notesViewState,
                areasState = state.areasViewState,
                tasksState = state.tasksViewState,
                areaClick = {
                    scope.launch {
                        selectedAreaId.value = it
                        areaDetailSheetState.show()
                    }
                },
                createNewAreaClick = {
                    scope.launch {
                        viewModel.dispatch(CreateNewAreaAction.Open)
                        createNewAreaSheetState.show()
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
            }
        )

        CreateNewNoteBottomSheet(
            state = state.createNewNoteViewState,
            modalSheetState = createNewNoteSheetState,
            onAreaSelect = {
                viewModel.dispatch(CreateNewNoteAction.AreaSelect(it))
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

            }
        )

        AreaDetailScreen(
            modalSheetState = areaDetailSheetState,
            areaId = selectedAreaId.value,
            onAreaChanged = { viewModel.refreshData() }
        )

    }

    LaunchedEffect(Unit) {
        combine(
            snapshotFlow { createNewAreaSheetState.currentValue },
            snapshotFlow { areaDetailSheetState.currentValue },
            snapshotFlow { createNewNoteSheetState.currentValue },
        ) { createNewAreaState, areaDetailState, createNewNoteState ->
            val hideBottomBar =
                createNewAreaState != ModalBottomSheetValue.Hidden
                        || areaDetailState != ModalBottomSheetValue.Hidden
                        || createNewNoteState != ModalBottomSheetValue.Hidden
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

                is CreateNewNoteEvent.CreatedSuccess -> {
                    createNewNoteSheetState.hide()
                }
            }
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
    areaClick: (Int) -> Unit,
    createNewAreaClick: () -> Unit,
    addExistingAreaClick: () -> Unit,
    createGoalClick: () -> Unit,
    createNoteClick: () -> Unit
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        beyondBoundsPageCount = 2
    ) { index ->
        when (index) {
            0 -> NotesView(
                isLoading = notesState.isLoading,
                createGoalClick = createGoalClick,
                createNoteClick = createNoteClick
            )

            1 -> AreasView(
                items = areasState.items,
                isLoading = areasState.isLoading,
                areaClick = areaClick,
                createNewAreaClick = createNewAreaClick,
                addExistingAreaClick = addExistingAreaClick
            )

            else -> TasksView(
                isLoading = tasksState.isLoading
            )
        }
    }
}

