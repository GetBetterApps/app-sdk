package com.velkonost.getbetter.android.features.taskdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.taskdetail.components.TaskDetailHeader
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.shared.features.taskdetail.presentation.TaskDetailViewModel

@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading || state.task == null) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 220.dp)
            ) {
                item {
                    TaskDetailHeader(
                        isFavorite = state.task!!.isFavorite,
                        isFavoriteLoading = state.task!!.isFavoriteLoading,
                        onBackClick = {
//                            viewModel.dispatch(NavigateBack)
                        },
                        onFavoriteClick = {

                        }
                    )
                }
            }
        }
    }


}