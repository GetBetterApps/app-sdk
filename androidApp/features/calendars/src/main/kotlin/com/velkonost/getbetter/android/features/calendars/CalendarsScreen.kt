package com.velkonost.getbetter.android.features.calendars

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.android.features.calendars.components.ActionItem
import com.velkonost.getbetter.android.features.calendars.components.CalendarDateItem
import com.velkonost.getbetter.android.features.calendars.extension.OnSideReached
import com.velkonost.getbetter.android.features.calendars.extension.centerItem
import com.velkonost.getbetter.android.features.profiledetail.ProfileDetailScreen
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.PlaceholderView
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
import com.velkonost.getbetter.shared.features.calendars.presentation.CalendarsViewModel
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalendarsScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarsViewModel,
    forceHideBottomBar: MutableState<Boolean> = mutableStateOf(false)
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val listState = rememberLazyListState()

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

    Column(modifier = modifier.padding(top = 40.dp)) {
        AnimatedContent(
            targetState = state.datesState.selectedDate?.monthDay,
            label = ""
        ) { selectedDate ->
            selectedDate?.let {
                Column(
                    modifier = modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = it.toString(LocalContext.current).replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                            else it.toString()
                        },
                        style = MaterialTheme.typography.headlineSmall,
                        color = colorResource(resource = SharedR.colors.text_secondary)
                    )

                    Text(
                        modifier = modifier.padding(top = 6.dp),
                        text = state.datesState.selectedDate?.year?.toString(LocalContext.current)
                            ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )
                }
            }
        }

        LazyRow(
            modifier = modifier.padding(top = 12.dp),
            state = listState,
            contentPadding = PaddingValues(start = 16.dp)
        ) {
            items(state.datesState.items, key = { it.id }) { item ->
                CalendarDateItem(
                    item = item,
                    isSelected = item.id == state.datesState.selectedDate?.id,
                    onClick = {
                        viewModel.dispatch(CalendarsAction.DateClick(it))
                    }
                )
            }
        }

        if (state.datesState.selectedDate != null) {
            if (state.datesState.selectedDate!!.isLoading) {
                Box(modifier = modifier.fillMaxSize()) {
                    Loader(modifier.align(Alignment.Center))
                }
            } else {
                Box {
                    if (state.datesState.selectedDate?.items.isNullOrEmpty()) {
                        PlaceholderView(
                            text = stringResource(
                                resource =
                                if (state.datesState.selectedDate!!.isPast) SharedR.strings.placeholder_calendars_day_past
                                else SharedR.strings.placeholder_calendars_day_future
                            )
                        )
                    } else {
                        LazyColumn(
                            modifier = modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 160.dp)
                        ) {
                            items(state.datesState.selectedDate?.items!!) { item ->
                                ActionItem(
                                    item = item,
                                    onAreaClick = {
                                        scope.launch {
                                            selectedAreaId.value =
                                                (item.relatedData as Area).id
                                            areaDetailSheetState.show()
                                        }
                                    },
                                    onNoteClick = {
                                        viewModel.dispatch(CalendarsAction.NoteClick(it))
                                    },
                                    onUserClick = {
                                        scope.launch {
                                            selectedUserId.value =
                                                (item.data as UserInfoShort).id
                                            profileDetailSheetState.show()
                                        }
                                    },
                                    onTaskClick = {
                                        viewModel.dispatch(CalendarsAction.TaskClick(it))
                                    }
                                )
                            }
                        }
                    }

                    Box(
                        modifier = modifier
                            .height(30.dp)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        colorResource(resource = SharedR.colors.main_background),
                                        colorResource(resource = SharedR.colors.main_background)
                                            .copy(alpha = 0.7f),
                                        Color.Transparent,
                                    ),
                                ),
                            )
                    )
                }
            }
        }
    }

    LaunchedEffect(state.datesState.selectedDate) {
        scope.launch {
            val selectedItemIndex = state.datesState.items.indexOfFirst {
                it.id == state.datesState.selectedDate?.id
            }
            listState.centerItem(selectedItemIndex)
        }
    }

    listState.OnSideReached(
        isLoadingRight = state.datesState.isNextLoading,
        onLoadMoreRight = { viewModel.dispatch(CalendarsAction.LoadMoreNextDates) },
    )

    AreaDetailScreen(
        modalSheetState = areaDetailSheetState,
        areaId = selectedAreaId.value,
        onAreaChanged = {
//            viewModel.dispatch(NoteDetailAction.AreaChanged)
        }
    )

    ProfileDetailScreen(
        modalSheetState = profileDetailSheetState,
        userId = selectedUserId.value
    )

    LaunchedEffect(Unit) {
        combine(
            snapshotFlow { profileDetailSheetState.currentValue },
            snapshotFlow { areaDetailSheetState.currentValue },
        ) { profileDetailState, areaDetailState ->
            val hideBottomBar =
                profileDetailState != ModalBottomSheetValue.Hidden
                        || areaDetailState != ModalBottomSheetValue.Hidden
            hideBottomBar
        }.collect {
            forceHideBottomBar.value = it
        }
    }

}