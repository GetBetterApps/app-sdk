package com.velkonost.getbetter.android.features.abilitydetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.AbilityDetailsViewModel

@Composable
fun AbilityDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AbilityDetailsViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.tabs.size })

    Box(modifier = modifier.fillMaxSize()) {

    }
}