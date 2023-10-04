package com.velkonost.getbetter.android.features.addarea

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.velkonost.getbetter.android.features.addarea.components.AddAreaItem
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.core.compose.extensions.items
import com.velkonost.getbetter.shared.features.addarea.presentation.AddAreaViewModel

@Composable
fun AddAreaScreen(
    modifier: Modifier = Modifier,
    viewModel: AddAreaViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val areas = viewModel.pagingData.collectAsLazyPagingItems()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Column {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .fadingEdge(),
                    contentPadding = PaddingValues(bottom = 140.dp)
                ) {
                    val refresh = areas.loadState.refresh is LoadState.Loading
                    val append = areas.loadState.append is LoadState.Loading

                    if (!refresh) {
                        items(items = areas) { item ->
                            item?.let {
                                AddAreaItem(item = item)
                            }
                        }
                    }

                    if (refresh || append) {
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
}