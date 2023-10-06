package com.velkonost.getbetter.android.features.addarea

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.addarea.components.AddAreaItem
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.features.addarea.presentation.AddAreaViewModel
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaClick
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.LoadNextPage

@Composable
fun AddAreaScreen(
    modifier: Modifier = Modifier,
    viewModel: AddAreaViewModel
) {
    val listState = rememberLazyListState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading && state.items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Column {
                LazyColumn(
                    state = listState,
                    modifier = modifier
                        .fillMaxSize()
                        .fadingEdge(),
                    contentPadding = PaddingValues(bottom = 140.dp)
                ) {

                    items(state.items, key = { area -> area.id }) { item ->
                        AddAreaItem(item = item) {
                            viewModel.dispatch(AddAreaClick(it))
                        }
                    }

                    if (state.isLoading) {
                        item {
                            Box(modifier = modifier.fillMaxSize()) {
                                Loader(modifier = modifier.align(Alignment.Center))
                            }
                        }
                    }

                }
            }
        }
    }

    listState.OnBottomReached(
        buffer = state.loadMorePrefetch,
        isLoading = state.isLoading
    ) {
        viewModel.dispatch(LoadNextPage)
    }
}