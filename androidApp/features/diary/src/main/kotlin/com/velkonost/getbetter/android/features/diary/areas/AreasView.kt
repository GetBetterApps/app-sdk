package com.velkonost.getbetter.android.features.diary.areas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.areas.components.AddAreaItem
import com.velkonost.getbetter.android.features.diary.areas.components.AreaItem
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import model.Area

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AreasView(
    modifier: Modifier = Modifier,
    items: List<Area>,
    createNewAreaClick: () -> Unit
) {

    Box {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .fadingEdge(),
            contentPadding = PaddingValues(bottom = 140.dp)
        ) {
            items(items) { item ->
                AreaItem(item) {

                }
            }
//            items(20) {
//
//            }

        }

        AddAreaItem(
            addExistingClick = {

            },
            createNewClick = {
                createNewAreaClick.invoke()
            }
        )

    }
}
