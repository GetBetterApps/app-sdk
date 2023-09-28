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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.diary.areas.AreasView
import com.velkonost.getbetter.android.features.diary.areas.components.createnewarea.CreateNewAreaBottomSheet
import com.velkonost.getbetter.android.features.diary.notes.NotesView
import com.velkonost.getbetter.android.features.diary.tasks.TasksView
import com.velkonost.getbetter.core.compose.components.PrimaryTabs
import com.velkonost.getbetter.shared.features.diary.DiaryViewModel
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaAction
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DiaryScreen(
    modifier: Modifier = Modifier,
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

    Box {
        Column {
            PrimaryTabs(
                tabs = state.tabs.map { it.title.toString(LocalContext.current) },
                pagerState = pagerState
            )
            DiaryScreenContent(
                pagerState = pagerState,
                createNewAreaClick = {
                    scope.launch {
                        viewModel.dispatch(CreateNewAreaAction.Open)
                        createNewAreaSheetState.show()
                    }
                }
            )
        }

        CreateNewAreaBottomSheet(
            modalSheetState = createNewAreaSheetState,
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
            onCreateClick = {
                viewModel.dispatch(CreateNewAreaAction.CreateClick)
            }
        )
    }

    LaunchedEffect(Unit) {
        snapshotFlow { createNewAreaSheetState.currentValue }
            .collect {
                forceHideBottomBar.value = it != ModalBottomSheetValue.Hidden
            }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryScreenContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,

    createNewAreaClick: () -> Unit
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        beyondBoundsPageCount = 2
    ) { index ->
        when (index) {
            0 -> NotesView()
            1 -> AreasView(
                createNewAreaClick = createNewAreaClick
            )

            else -> TasksView()
        }
    }
}

