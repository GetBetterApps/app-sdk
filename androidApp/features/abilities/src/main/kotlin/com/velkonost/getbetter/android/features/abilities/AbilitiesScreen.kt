package com.velkonost.getbetter.android.features.abilities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.abilities.components.AbilityItem
import com.velkonost.getbetter.core.compose.components.HintButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.ad.AdView
import com.velkonost.getbetter.core.compose.composable.OnLifecycleEvent
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.shared.features.abilities.presentation.AbilitiesViewModel
import com.velkonost.getbetter.shared.features.abilities.presentation.contract.AbilitiesAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AbilitiesScreen(
    modifier: Modifier = Modifier,
    viewModel: AbilitiesViewModel
) {

    val listState = rememberLazyListState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading && state.items.size <= 1) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                state = listState,
                contentPadding = PaddingValues(
                    bottom = 140.dp,
                    top = 40.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            ) {

                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(resource = SharedR.strings.task_abilities_title),
                            style = MaterialTheme.typography.headlineSmall,
                            color = colorResource(resource = SharedR.colors.text_title),
                        )
                        Spacer(modifier = modifier.weight(1f))
                        HintButton {
                            viewModel.dispatch(AbilitiesAction.HintClick)
                        }
                    }
                }

                itemsIndexed(state.items, key = { _, it -> it.name }) { index, item ->
                    AbilityItem(
                        item = item,
                        onClick = {
                            viewModel.dispatch(AbilitiesAction.AbilityClick(it))
                        }
                    )

                    if (index % state.adPosition == 0 && index != 0) {
                        AdView(
                            slotId = state.adId,
                            padding = 0.dp
                        )
                    }
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

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onAppear()
            }

            else -> {}
        }
    }
}