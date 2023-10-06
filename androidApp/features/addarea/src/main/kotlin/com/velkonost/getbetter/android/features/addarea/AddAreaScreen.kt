package com.velkonost.getbetter.android.features.addarea

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.addarea.components.AddAreaItem
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.features.addarea.presentation.AddAreaViewModel
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaClick
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.LoadNextPage
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AddAreaScreen(
    modifier: Modifier = Modifier,
    viewModel: AddAreaViewModel
) {
    val listState = rememberLazyListState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading && state.items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Column {

                Row(
                    modifier = modifier.padding(top = 40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = modifier
                            .padding(start = 20.dp)
                            .size(42.dp)
                            .background(
                                color = colorResource(resource = SharedR.colors.background_icon),
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(4.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                viewModel.dispatch(NavigateBack)
                            },
                        painter = painterResource(imageResource = SharedR.images.ic_arrow_back),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            color = colorResource(resource = SharedR.colors.icon_active)
                        )
                    )

                    Text(
                        modifier = modifier.padding(start = 12.dp),
                        text = "Public Areas",
                        style = MaterialTheme.typography.headlineSmall,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                }


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