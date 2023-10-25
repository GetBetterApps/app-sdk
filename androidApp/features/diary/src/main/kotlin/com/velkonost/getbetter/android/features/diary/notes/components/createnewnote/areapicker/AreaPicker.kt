package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.areapicker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.extensions.horizontalFadingEdge
import com.velkonost.getbetter.shared.core.util.randomUUID
import model.Area

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AreaPicker(
    modifier: Modifier = Modifier,
    areas: List<Area>,
    selectedArea: Area?,
    isAreaPickerVisible: MutableState<Boolean>,
    onAreaSelect: (Area) -> Unit
) {
    val areasPagerState = rememberPagerState(initialPage = 0, pageCount = { areas.size })

    PrimaryBox(padding = 0) {
        Column {
            AreaPickerHeader(
                selectedAreaName = selectedArea?.name,
                isAreaPickerVisible = isAreaPickerVisible
            )

            AnimatedVisibility(visible = isAreaPickerVisible.value) {
                HorizontalPager(
                    modifier = modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .horizontalFadingEdge()
                        .padding(bottom = 16.dp),
                    state = areasPagerState,
                    contentPadding = PaddingValues(start = 18.dp, end = 18.dp),
                    key = {
                        if (areas.isNotEmpty()) {
                            areas[it].id
                        } else randomUUID()
                    }
                ) {
                    if (areas.isEmpty()) {
                        return@HorizontalPager
                    }

                    AreaPickerItem(area = areas[it])
                }
            }

        }
    }

    LaunchedEffect(areas) {
        snapshotFlow { areasPagerState.currentPage }
            .collect { page ->
                if (areas.isNotEmpty()) {
                    onAreaSelect.invoke(areas[page])
                }
            }
    }
}