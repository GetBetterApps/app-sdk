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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.android.features.diary.areas.AreasView
import com.velkonost.getbetter.android.features.diary.areas.components.createnewarea.CreateNewAreaBottomSheet
import com.velkonost.getbetter.android.features.diary.notes.NotesView
import com.velkonost.getbetter.android.features.diary.tasks.TasksView
import com.velkonost.getbetter.core.compose.components.PrimaryTabs
import com.velkonost.getbetter.shared.features.diary.DiaryViewModel
import com.velkonost.getbetter.shared.features.diary.contracts.AddAreaClick
import com.velkonost.getbetter.shared.features.diary.contracts.AreasViewState
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaEvent
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

    val areaDetailSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val selectedAreaId = remember { mutableStateOf("") }

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

                },
                createNoteClick = {

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

        AreaDetailScreen(
            modalSheetState = areaDetailSheetState,
            areaId = selectedAreaId.value
        )

    }

    LaunchedEffect(Unit) {
        snapshotFlow { createNewAreaSheetState.currentValue }
            .combine(
                snapshotFlow { areaDetailSheetState.currentValue }
            ) { createNewAreaState, areaDetailState ->
                val hideBottomBar =
                    createNewAreaState != ModalBottomSheetValue.Hidden
                            || areaDetailState != ModalBottomSheetValue.Hidden
                hideBottomBar
            }
            .collect {
                forceHideBottomBar.value = it
            }
    }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest {
            when (it) {
                is CreateNewAreaEvent.CreatedSuccess -> {
                    createNewAreaSheetState.hide()
                }
            }
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
    areaClick: (String) -> Unit,
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

