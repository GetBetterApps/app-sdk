package com.velkonost.getbetter.android.features.calendars

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.calendars.components.AreaActionItem
import com.velkonost.getbetter.android.features.calendars.components.NoteActionItem
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.features.calendars.presentation.CalendarsViewModel
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsAction
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.DateUIItem
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun CalendarsScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarsViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val listState = rememberLazyListState()

    Column(
        modifier = modifier.padding(top = 40.dp)
    ) {

        AnimatedContent(targetState = state.datesState.selectedDate, label = "") { selectedDate ->
            selectedDate?.let {
                Column(
                    modifier = modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = it.monthDay.toString(LocalContext.current).replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                            else it.toString()
                        },
                        style = MaterialTheme.typography.headlineSmall,
                        color = colorResource(resource = SharedR.colors.text_secondary)
                    )

                    Text(
                        modifier = modifier.padding(top = 6.dp),
                        text = it.year.toString(LocalContext.current),
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
            LazyColumn {
                items(state.datesState.selectedDate?.items!!, key = { it.id }) { item ->
                    PrimaryBox {
                        Column {
                            if (item.description != null) {
                                Text(
                                    text = item.description!!.toString(LocalContext.current),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colorResource(resource = SharedR.colors.text_primary)
                                )
                            }
                            Spacer(modifier.height(12.dp))

                            if (item.data is Area) {
                                AreaActionItem(
                                    item = item.data as Area,
                                    onClick = {

                                    },
                                    onLikeClick = {

                                    }
                                )
                            } else if (item.data is Note) {
                                NoteActionItem(
                                    item = item.data as Note,
                                    onClick = {

                                    },
                                    onLikeClick = {

                                    }
                                )
                            }
                        }
                    }
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

}

@Composable
fun CalendarDateItem(
    modifier: Modifier = Modifier,
    item: DateUIItem,
    isSelected: Boolean,
    onClick: (Long) -> Unit
) {

    Column(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.small,
            )
            .background(
                color = colorResource(
                    resource = if (isSelected) SharedR.colors.button_gradient_start
                    else SharedR.colors.background_item
                ), shape = MaterialTheme.shapes.small
            )
            .size(52.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) { onClick.invoke(item.id) },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.dayOfWeek.toString(LocalContext.current).uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(
                resource = if (isSelected) SharedR.colors.text_light
                else SharedR.colors.text_primary
            )
        )

        Text(
            text = item.day.toString(LocalContext.current),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(
                resource = if (isSelected) SharedR.colors.text_light
                else SharedR.colors.text_primary
            )
        )
    }
}

suspend fun LazyListState.centerItem(index: Int) {
    suspend fun locateTarget(): Boolean {
        val layoutInfo = layoutInfo
        val containerSize =
            layoutInfo.viewportSize.width - layoutInfo.beforeContentPadding - layoutInfo.afterContentPadding

        val target = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
            ?: return false
        val targetOffset = containerSize / 2f - target.size / 2f
        animateScrollBy(target.offset - targetOffset)
        return true
    }

    if (!locateTarget()) {
        val visibleItemsInfo = layoutInfo.visibleItemsInfo
        val currentIndex = visibleItemsInfo.getOrNull(visibleItemsInfo.size / 2)?.index ?: -1
        scrollToItem(
            if (index > currentIndex) {
                (index - visibleItemsInfo.size + 1)
            } else {
                index
            }.coerceIn(0, layoutInfo.totalItemsCount)
        )
        locateTarget()
    }
}

@Composable
fun LazyListState.OnSideReached(
    buffer: Int = 5,
    isLoadingRight: Boolean,
    onLoadMoreRight: () -> Unit,
) {
    val shouldLoadMoreRight = remember {
        derivedStateOf {
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMoreRight, isLoadingRight) {
        snapshotFlow { shouldLoadMoreRight.value && !isLoadingRight }.collect {
            if (it) onLoadMoreRight()
        }
    }
}