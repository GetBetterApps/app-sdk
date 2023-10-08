package com.velkonost.getbetter.android.features.addarea

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.addarea.components.AddAreaHeader
import com.velkonost.getbetter.android.features.addarea.components.AddAreaList
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.shared.features.addarea.presentation.AddAreaViewModel
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaClick
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.LoadNextPage
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.NavigateBack
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddAreaScreen(
    modifier: Modifier = Modifier,
    viewModel: AddAreaViewModel
) {
    val listState = rememberLazyListState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val areaDetailSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val selectedAreaId = remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading && state.items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Column {
                AddAreaHeader { viewModel.dispatch(NavigateBack) }
                AddAreaList(
                    items = state.items,
                    listState = listState,
                    isLoading = state.isLoading,
                    onAreaClick = {
                        scope.launch {
                            selectedAreaId.value = it
                            areaDetailSheetState.show()
                        }
                    },
                    onAddAreaClick = {
                        viewModel.dispatch(AddAreaClick(it))
                    },
                )
            }
        }

        AreaDetailScreen(
            modalSheetState = areaDetailSheetState,
            areaId = selectedAreaId.value
        )
    }

    listState.OnBottomReached(
        buffer = state.loadMorePrefetch,
        isLoading = state.isLoading
    ) {
        viewModel.dispatch(LoadNextPage)
    }
}