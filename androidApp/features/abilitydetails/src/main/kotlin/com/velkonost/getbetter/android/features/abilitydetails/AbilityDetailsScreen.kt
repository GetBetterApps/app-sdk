package com.velkonost.getbetter.android.features.abilitydetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.abilitydetails.components.AbilityDetailsHeader
import com.velkonost.getbetter.android.features.abilitydetails.components.AbilityMotivationContent
import com.velkonost.getbetter.android.features.abilitydetails.components.AbilityNotesContent
import com.velkonost.getbetter.core.compose.components.PrimaryTabs
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.AbilityDetailsViewModel
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsAction

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AbilityDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AbilityDetailsViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.tabs.size })

    val tabsTopPadding: Float by animateFloatAsState(
        targetValue = if (pagerState.currentPage == 0) 110F else 40F,
        animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing),
        label = ""
    )

    Box(modifier = modifier.fillMaxSize()) {

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
            beyondBoundsPageCount = 2
        ) { index ->
            when (index) {
                0 -> AbilityNotesContent(
                    isLoading = state.userNotesViewState.isLoading,
                    items = state.userNotesViewState.items,
                    loadMorePrefetch = state.userNotesViewState.loadMorePrefetch,
                    itemClick = {

                    },
                    itemLikeClick = {

                    },
                    onBottomReach = {
                        viewModel.dispatch(AbilityDetailsAction.UserNotesLoadNextPage)
                    }
                )

                1 -> AbilityMotivationContent(
                    items = state.affirmations,
                    isActive = pagerState.currentPage == 1
                )
            }
        }

        AnimatedVisibility(visible = pagerState.currentPage == 0) {
            AbilityDetailsHeader(
                title = state.ability?.name ?: "",
                onBackClick = {
                    viewModel.dispatch(AbilityDetailsAction.NavigateBack)
                }
            )
        }

        PrimaryTabs(
            tabs = state.tabs.map { it.title.toString(LocalContext.current) },
            pagerState = pagerState,
            topPadding = tabsTopPadding.toInt()
        )

    }

}