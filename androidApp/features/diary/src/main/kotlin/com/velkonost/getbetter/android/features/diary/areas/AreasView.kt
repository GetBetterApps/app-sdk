package com.velkonost.getbetter.android.features.diary.areas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.areas.components.AddAreaButton
import com.velkonost.getbetter.android.features.diary.areas.components.AreaItem
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.PlaceholderView
import com.velkonost.getbetter.core.compose.components.ad.AdView
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AreasView(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    items: List<Area>,
    adPosition: Int,
    adSlotId: Int,
    itemClick: (Int) -> Unit,
    itemLikeClick: (Area) -> Unit,
    createNewAreaClick: () -> Unit,
    addExistingAreaClick: () -> Unit
) {

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            if (items.isEmpty()) {
                PlaceholderView(text = stringResource(resource = SharedR.strings.placeholder_diary_areas))
            } else {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .fadingEdge(),
                    contentPadding = PaddingValues(bottom = 140.dp)
                ) {
                    itemsIndexed(items, key = { _, it -> it.id }) { index, item ->
                        AreaItem(
                            item = item,
                            onClick = itemClick,
                            onLikeClick = itemLikeClick
                        )

                        if (index % adPosition == 0 && index != 0) {
                            AdView(slotId = adSlotId)
                        }
                    }

                    if (items.size < adPosition) {
                        item {
                            AdView(slotId = adSlotId)
                        }
                    }
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
