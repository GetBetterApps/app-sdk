package com.velkonost.getbetter.android.features.diary.areas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.areas.components.AddAreaButton
import com.velkonost.getbetter.android.features.diary.areas.components.AreaItem
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.core.model.area.Area

@Composable
fun AreasView(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    items: List<Area>,
    itemClick: (Int) -> Unit,
    itemLikeClick: (Area) -> Unit,
    createNewAreaClick: () -> Unit,
    addExistingAreaClick: () -> Unit
) {

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .fadingEdge(),
                contentPadding = PaddingValues(bottom = 140.dp)
            ) {
                items(items) { item ->
                    AreaItem(
                        item = item,
                        onClick = itemClick,
                        onLikeClick = itemLikeClick
                    )
                }
            }

            AddAreaButton(
                addExistingClick = {
                    addExistingAreaClick.invoke()
                },
                createNewClick = {
                    createNewAreaClick.invoke()
                }
            )
        }

    }
}
