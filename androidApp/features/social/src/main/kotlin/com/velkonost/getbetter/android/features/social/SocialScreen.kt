package com.velkonost.getbetter.android.features.social

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.PrimaryTabs
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.features.social.SocialViewModel
import com.velkonost.getbetter.shared.features.social.contracts.FeedViewState
import com.velkonost.getbetter.shared.features.social.contracts.SocialAction

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SocialScreen(
    modifier: Modifier = Modifier,
    viewModel: SocialViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.tabs.size })


    Box {
        Column {
            PrimaryTabs(
                tabs = state.tabs.map { it.title.toString(LocalContext.current) },
                pagerState = pagerState
            )

            SocialScreenContent(
                pagerState = pagerState,
                generalFeedState = state.generalFeed,
                areasFeedState = state.areasFeed,
                noteClick = {
                    viewModel.dispatch(SocialAction.NoteClick(it))
                },
                generalFeedLoadNextPage = {
                    viewModel.dispatch(SocialAction.GeneralFeedLoadNextPage)
                },
                areasFeedLoadNextPage = {
                    viewModel.dispatch(SocialAction.AreasFeedLoadNextPage)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SocialScreenContent(
    pagerState: PagerState,
    generalFeedState: FeedViewState,
    areasFeedState: FeedViewState,
    noteClick: (Note) -> Unit,
    generalFeedLoadNextPage: () -> Unit,
    areasFeedLoadNextPage: () -> Unit
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        beyondBoundsPageCount = 2
    ) { index ->
        when (index) {
            0 -> SocialFeedView(
                loadMorePrefetch = generalFeedState.loadMorePrefetch,
                isLoading = generalFeedState.isLoading,
                items = generalFeedState.items,
                itemClick = noteClick,
                onBottomReach = generalFeedLoadNextPage
            )

            else -> SocialFeedView(
                loadMorePrefetch = areasFeedState.loadMorePrefetch,
                isLoading = areasFeedState.isLoading,
                items = areasFeedState.items,
                itemClick = noteClick,
                onBottomReach = areasFeedLoadNextPage
            )
        }
    }
}

@Composable
fun SocialFeedView(
    modifier: Modifier = Modifier,
    loadMorePrefetch: Int,
    isLoading: Boolean,
    items: List<Note>,
    itemClick: (Note) -> Unit,
    onBottomReach: () -> Unit
) {
    val listState = rememberLazyListState()

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading && items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .fadingEdge(),
                state = listState,
                contentPadding = PaddingValues(bottom = 140.dp)
            ) {
                items(items) { item ->
                    NoteItem(
                        item = item,
                        onClick = itemClick
                    )
                }

                if (isLoading) {
                    item {
                        Box(modifier = modifier.fillMaxSize()) {
                            Loader(modifier = modifier.align(Alignment.Center))
                        }
                    }
                }

            }
        }
    }

    listState.OnBottomReached(
        buffer = loadMorePrefetch,
        isLoading = isLoading,
        onLoadMore = onBottomReach
    )
}