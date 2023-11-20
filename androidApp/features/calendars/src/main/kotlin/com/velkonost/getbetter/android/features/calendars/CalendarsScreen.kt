package com.velkonost.getbetter.android.features.calendars

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.shared.features.calendars.presentation.CalendarsViewModel
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsAction
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.DateUIItem
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

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
        LazyRow(
            state = listState
        ) {
            items(state.datesState.items, key = { it.id }) { item ->
                CalendarDateItem(
                    item = item,
                    isSelected = item.id == state.datesState.selectedDateId,
                    onClick = {
                        viewModel.dispatch(CalendarsAction.DateClick(it))
                    }
                )
            }
        }
    }

    LaunchedEffect(state.datesState.selectedDateId) {
        scope.launch {
            delay(1.seconds)
            val selectedItemIndex = state.datesState.items.indexOfFirst {
                it.id == state.datesState.selectedDateId
            }
            listState.centerItem(selectedItemIndex)
        }
    }

}

@Composable
fun CalendarDateItem(
    modifier: Modifier = Modifier,
    item: DateUIItem,
    isSelected: Boolean,
    onClick: (Long) -> Unit
) {

    AnimatedContent(targetState = isSelected, label = "") {
        Column(
            modifier = modifier
                .padding(4.dp)
                .background(
                    color = colorResource(
                        resource = if (it) SharedR.colors.button_gradient_start
                        else SharedR.colors.background_item
                    ),
                    shape = MaterialTheme.shapes.medium
                )
                .size(64.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onClick.invoke(item.id) },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MON",
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(
                    resource = if (it) SharedR.colors.text_light
                    else SharedR.colors.text_secondary
                )
            )

            Text(
                modifier = modifier.padding(top = 6.dp),
                text = item.date.toString(LocalContext.current),
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(
                    resource = if (it) SharedR.colors.text_light
                    else SharedR.colors.text_secondary
                )
            )
        }
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