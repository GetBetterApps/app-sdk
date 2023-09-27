package com.velkonost.getbetter.android.features.diary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.android.features.diary.areas.AreasView
import com.velkonost.getbetter.android.features.diary.notes.NotesView
import com.velkonost.getbetter.android.features.diary.tasks.TasksView
import com.velkonost.getbetter.core.compose.components.PrimaryTabs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryScreen(
    modifier: Modifier = Modifier
) {
    val tabsData = listOf("Diary", "Oblasti", "Tasks")
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabsData.size })
    Column {
        PrimaryTabs(tabs = tabsData, pagerState = pagerState)
        DiaryScreenContent(pagerState = pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryScreenContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        beyondBoundsPageCount = 2
    ) { index ->
        when (index) {
            0 -> NotesView()
            1 -> AreasView()
            else -> TasksView()
        }
    }
}
