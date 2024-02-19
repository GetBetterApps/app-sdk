package com.velkonost.getbetter.core.compose.components.note.areapicker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.extensions.horizontalFadingEdge
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_150
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_38
import com.velkonost.getbetter.core.compose.theme.Pixel.PX_ZERO
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.util.randomUUID
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun AreaPicker(
    modifier: Modifier = Modifier,
    areas: List<Area>,
    areasPagerState: PagerState = rememberPagerState(initialPage = 0, pageCount = { areas.size }),
    selectedArea: Area?,
    noteType: NoteType,
    isAreaPickerVisible: MutableState<Boolean>,
    modalSheetState: ModalBottomSheetState,
    onAreaSelect: (Area) -> Unit
) {

    val pagerHeight = remember { DP_150 }
    val viewPadding = remember { PX_ZERO }
    val contentPadding = remember { DP_38 }
    val pagerBottomPadding = remember { DP_16 }

    val scope = rememberCoroutineScope()

    PrimaryBox(padding = viewPadding) {
        Column {
            AreaPickerHeader(
                selectedArea = selectedArea,
                isAreaPickerVisible = isAreaPickerVisible,
                noteType = noteType
            )

            AnimatedVisibility(visible = isAreaPickerVisible.value) {
                HorizontalPager(
                    modifier = modifier
                        .height(pagerHeight)
                        .fillMaxWidth()
                        .horizontalFadingEdge()
                        .padding(bottom = pagerBottomPadding),
                    state = areasPagerState,
                    contentPadding = PaddingValues(horizontal = contentPadding),
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

    LaunchedEffect(modalSheetState.currentValue) {
        scope.launch {
            areasPagerState.animateScrollToPage(0)
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