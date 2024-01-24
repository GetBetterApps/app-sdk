package com.velkonost.getbetter.android.features.social

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.social.components.FeedNoteItem
import com.velkonost.getbetter.core.compose.components.HintButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.PlaceholderView
import com.velkonost.getbetter.core.compose.components.PrimaryTabs
import com.velkonost.getbetter.core.compose.components.ad.AdView
import com.velkonost.getbetter.core.compose.composable.OnLifecycleEvent
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.features.social.SocialViewModel
import com.velkonost.getbetter.shared.features.social.contracts.FeedViewState
import com.velkonost.getbetter.shared.features.social.contracts.SocialAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SocialScreen(
    viewModel: SocialViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.tabs.size })

    Box {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PrimaryTabs(
                    tabs = state.tabs.map { it.title.toString(LocalContext.current) },
                    pagerState = pagerState,
                    widthFraction = 0.9f
                )

                HintButton(modifier = Modifier.padding(top = 40.dp)) {
                    viewModel.dispatch(SocialAction.HintClick)
                }
            }

            Box(modifier = Modifier.padding(top = 6.dp)) {
                SocialScreenContent(
                    pagerState = pagerState,
                    generalFeedState = state.generalFeed,
                    areasFeedState = state.areasFeed,
                    adPosition = state.adPosition,
                    noteClick = {
                        viewModel.dispatch(SocialAction.NoteClick(it))
                    },
                    noteLikeClick = {
                        viewModel.dispatch(SocialAction.NoteLikeClick(it))
                    },
                    generalFeedLoadNextPage = {
                        viewModel.dispatch(SocialAction.GeneralFeedLoadNextPage)
                    },
                    areasFeedLoadNextPage = {
                        viewModel.dispatch(SocialAction.AreasFeedLoadNextPage)
                    },
                    onRefreshGeneralFeed = {
                        viewModel.dispatch(SocialAction.RefreshGeneralFeed)
                    },
                    onRefreshAreasFeed = {
                        viewModel.dispatch(SocialAction.RefreshAreasFeed)
                    }
                )

                Box(
                    modifier = Modifier
                        .height(60.dp)
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

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onAppear()
            }

            else -> {}
        }
    }

    LaunchedEffect(Unit) {

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SocialScreenContent(
    pagerState: PagerState,
    generalFeedState: FeedViewState,
    areasFeedState: FeedViewState,
    adPosition: Int,
    noteClick: (Note) -> Unit,
    noteLikeClick: (Note) -> Unit,
    generalFeedLoadNextPage: () -> Unit,
    areasFeedLoadNextPage: () -> Unit,
    onRefreshGeneralFeed: () -> Unit,
    onRefreshAreasFeed: () -> Unit
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
                isRefreshing = generalFeedState.isRefreshing,
                emptyText = stringResource(resource = SharedR.strings.placeholder_social_all),
                items = generalFeedState.items,
                adPosition = adPosition,
                itemClick = noteClick,
                itemLikeClick = noteLikeClick,
                onBottomReach = generalFeedLoadNextPage,
                onRefresh = onRefreshGeneralFeed
            )

            else -> SocialFeedView(
                loadMorePrefetch = areasFeedState.loadMorePrefetch,
                isLoading = areasFeedState.isLoading,
                isRefreshing = areasFeedState.isRefreshing,
                emptyText = stringResource(resource = SharedR.strings.placeholder_social_areas),
                items = areasFeedState.items,
                adPosition = adPosition,
                itemClick = noteClick,
                itemLikeClick = noteLikeClick,
                onBottomReach = areasFeedLoadNextPage,
                onRefresh = onRefreshAreasFeed
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SocialFeedView(
    modifier: Modifier = Modifier,
    loadMorePrefetch: Int,
    isLoading: Boolean,
    emptyText: String,
    isRefreshing: Boolean,
    items: List<Note>,
    adPosition: Int,
    itemClick: (Note) -> Unit,
    itemLikeClick: (Note) -> Unit,
    onBottomReach: () -> Unit,
    onRefresh: () -> Unit
) {
    val listState = rememberLazyListState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)

    val itemPosition = remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        if (isLoading && items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            if (items.isEmpty()) {
                PlaceholderView(text = emptyText)
            } else {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize(),
                    state = listState,
                    contentPadding = PaddingValues(bottom = 140.dp)
                ) {
                    itemsIndexed(
                        items,
                        key = { _, it -> it.id },
                        contentType = { _, it -> it.noteType }) { index, item ->
                        FeedNoteItem(
                            item = item,
                            onClick = itemClick,
                            onLikeClick = itemLikeClick
                        )

                        if (index % adPosition == 0 && index != 0) {
                            AdView()
                        }
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

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = colorResource(resource = SharedR.colors.button_gradient_start),
            contentColor = colorResource(resource = SharedR.colors.text_light)
        )
    }

    listState.OnBottomReached(
        buffer = loadMorePrefetch,
        isLoading = isLoading,
        onLoadMore = onBottomReach
    )
}

