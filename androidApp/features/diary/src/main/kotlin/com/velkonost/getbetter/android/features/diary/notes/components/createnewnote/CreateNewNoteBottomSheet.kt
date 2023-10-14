package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.extensions.horizontalFadingEdge
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.util.randomUUID
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import model.Area

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun CreateNewNoteBottomSheet(
    modifier: Modifier = Modifier,
    areas: List<Area>,
    state: CreateNewNoteViewState,
    modalSheetState: ModalBottomSheetState,
) {

    val areasPagerState = rememberPagerState(initialPage = 0, pageCount = { areas.size })
    val selectedArea = remember { mutableStateOf<Area?>(null) }
    val isAreaPickerVisible = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val areaArrowRotationAngle by animateFloatAsState(
        targetValue = if (isAreaPickerVisible.value) -90F else 90F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        label = ""
    )

    val interactionSource = remember { MutableInteractionSource() }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            if (state.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(20.dp)
                ) {
                    Loader(modifier = modifier.align(Alignment.Center))
                }
            } else {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(20.dp)
                ) {
                    Text(
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(
                            resource = SharedR.strings.diary_areas_create_new_area_title
                        ),
                        color = colorResource(resource = SharedR.colors.text_title),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    PrimaryBox(padding = 0) {
                        Column {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        isAreaPickerVisible.value = !isAreaPickerVisible.value
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    modifier = modifier.size(32.dp),
                                    painter = painterResource(imageResource = SharedR.images.emoji_1),
                                    contentDescription = null
                                )

                                Text(
                                    modifier = modifier.padding(start = 12.dp),
                                    text = selectedArea.value?.name ?: "select area for note",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = colorResource(resource = SharedR.colors.text_primary),
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Spacer(modifier = modifier.weight(1f))

                                Image(
                                    modifier = modifier
                                        .size(24.dp)
                                        .rotate(areaArrowRotationAngle),
                                    painter = painterResource(imageResource = SharedR.images.ic_arrow_right),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(
                                        color = colorResource(resource = SharedR.colors.icon_inactive)
                                    )
                                )
                            }

                            AnimatedVisibility(visible = isAreaPickerVisible.value) {
                                HorizontalPager(
                                    modifier = modifier
                                        .height(150.dp)
                                        .fillMaxWidth()
                                        .horizontalFadingEdge()
                                        .padding(bottom = 16.dp),
//                                    beyondBoundsPageCount = 4,
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

                                    val area = areas[it]

                                    Box(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)
                                            .padding(start = 2.dp, end = 2.dp)
                                            .background(
                                                color = colorResource(resource = SharedR.colors.text_field_background),
                                                shape = MaterialTheme.shapes.medium
                                            )
                                            .padding(16.dp)
                                    ) {
                                        Column(
                                            modifier = modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Image(
                                                modifier = modifier.size(64.dp),
                                                painter = painterResource(
                                                    imageResource = Emoji.getIconById(
                                                        area.emojiId!!
                                                    )
                                                ),
                                                contentDescription = null
                                            )

                                            Text(
                                                modifier = modifier.padding(top = 12.dp),
                                                text = area.name,
                                                color = colorResource(resource = SharedR.colors.text_primary),
                                                style = MaterialTheme.typography.titleLarge
                                            )
                                        }
                                    }
                                }
                            }

                        }

                    }
                }
            }
        }
    ) {

    }

    LaunchedEffect(areas) {
        snapshotFlow { areasPagerState.currentPage }
            .collect { page ->
                if (areas.isNotEmpty()) {
                    selectedArea.value = areas[page]
                }
            }
    }
}