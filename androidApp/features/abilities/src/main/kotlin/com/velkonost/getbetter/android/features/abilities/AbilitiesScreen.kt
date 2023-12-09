package com.velkonost.getbetter.android.features.abilities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.abilities.components.AbilityItem
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.shared.features.abilities.presentation.AbilitiesViewModel
import com.velkonost.getbetter.shared.features.abilities.presentation.contract.AbilitiesAction

@Composable
fun AbilitiesScreen(
    modifier: Modifier = Modifier,
    viewModel: AbilitiesViewModel
) {

    val listState = rememberLazyListState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box {
        if (state.isLoading && state.items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                state = listState,
                contentPadding = PaddingValues(bottom = 140.dp, top = 28.dp)
            ) {

                items(state.items, key = { it.name }) { item ->
                    AbilityItem(
                        item = item,
                        onClick = {

                        }
                    )
                }
            }
        }
    }

    listState.OnBottomReached(
        buffer = state.loadMorePrefetch,
        isLoading = state.isLoading,
        onLoadMore = {
            viewModel.dispatch(AbilitiesAction.LoadNextPage)
        }
    )
}