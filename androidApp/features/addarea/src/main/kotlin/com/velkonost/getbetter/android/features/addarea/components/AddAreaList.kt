package com.velkonost.getbetter.android.features.addarea.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.ad.AdView
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.features.addarea.presentation.model.AreaUI

@Composable
fun AddAreaList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    items: List<AreaUI>,
    onAreaClick: (Int) -> Unit,
    onAddAreaClick: (Int) -> Unit,
    isLoading: Boolean,
    adPosition: Int,
    adSlotId: Int
) {
    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .fadingEdge(),
        contentPadding = PaddingValues(bottom = 140.dp)
    ) {

        itemsIndexed(items, key = { _, area -> area.id }) { index, item ->
            AddAreaItem(
                item = item,
                onAreaClick = onAreaClick,
                onAddAreaClick = onAddAreaClick
            )

            if (index % adPosition == 0 && index != 0) {
                AdView(slotId = adSlotId)
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