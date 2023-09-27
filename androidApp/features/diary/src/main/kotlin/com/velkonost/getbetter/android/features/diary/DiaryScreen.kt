package com.velkonost.getbetter.android.features.diary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.areas.AreasView
import com.velkonost.getbetter.android.features.diary.notes.NotesView
import com.velkonost.getbetter.android.features.diary.tasks.TasksView
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.PrimaryTabs
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DiaryScreen(
    modifier: Modifier = Modifier,
    forceHideBottomBar: MutableState<Boolean> = mutableStateOf(false)
) {
    val tabsData = listOf("Diary", "Oblasti", "Tasks")
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabsData.size })

    val scope = rememberCoroutineScope()
    val createNewAreaSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true,
    )

    Box {
        Column {
            PrimaryTabs(tabs = tabsData, pagerState = pagerState)
            DiaryScreenContent(
                pagerState = pagerState,
                createNewAreaClick = {
                    scope.launch {

                        createNewAreaSheetState.show()
                    }
                }
            )
        }

        CreateNewAreaSheet(modalSheetState = createNewAreaSheetState)
    }

    LaunchedEffect(Unit) {
        snapshotFlow { createNewAreaSheetState.currentValue }
            .collect {
                forceHideBottomBar.value = it != ModalBottomSheetValue.Hidden
            }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryScreenContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    createNewAreaClick: () -> Unit
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        beyondBoundsPageCount = 2
    ) { index ->
        when (index) {
            0 -> NotesView()
            1 -> AreasView(
                createNewAreaClick = createNewAreaClick
            )

            else -> TasksView()
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateNewAreaSheet(
    modifier: Modifier = Modifier,
    modalSheetState: ModalBottomSheetState
) {

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .padding(20.dp)
            ) {

                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = "Create new area",
                    color = colorResource(resource = SharedR.colors.text_title),
                    style = MaterialTheme.typography.headlineSmall
                )

                Row(
                    modifier = modifier.padding(top = 24.dp)
                ) {
                    Box(
                        modifier = modifier
                            .shadow(
                                elevation = 8.dp,
                                shape = MaterialTheme.shapes.medium,
                            )
                            .background(
                                color = colorResource(resource = SharedR.colors.background_item),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(8.dp)
                    ) {
                        Image(
                            modifier = modifier
                                .size(48.dp)
                                .background(
                                    color = colorResource(resource = SharedR.colors.text_field_background),
                                    shape = MaterialTheme.shapes.medium
                                )
                                .padding(8.dp),
                            painter = painterResource(imageResource = SharedR.images.ic_menu_profile),
                            contentDescription = null
                        )
                    }

                    TextField(
                        value = "",
                        onValueChange = {

                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                            .height(64.dp)
                            .clip(shape = MaterialTheme.shapes.medium),
                        textStyle = MaterialTheme.typography.titleMedium
                            .copy(color = colorResource(resource = SharedR.colors.text_secondary)),
                        maxLines = 1,
                        placeholder = {
                            Text(
                                modifier = modifier.padding(top = 4.dp),
                                text = "placeholderText",
                                color = colorResource(resource = SharedR.colors.hint_color),
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
                            focusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
                            cursorColor = colorResource(resource = SharedR.colors.text_secondary)
                        ),
                    )
                }

                TextField(
                    value = "",
                    onValueChange = {

                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .wrapContentHeight()
                        .clip(shape = MaterialTheme.shapes.medium),
                    textStyle = MaterialTheme.typography.titleMedium
                        .copy(color = colorResource(resource = SharedR.colors.text_secondary)),
                    minLines = 3,
                    placeholder = {
                        Text(
                            modifier = modifier.padding(top = 4.dp),
                            text = "placeholderText",
                            color = colorResource(resource = SharedR.colors.hint_color),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
                        focusedContainerColor = colorResource(resource = SharedR.colors.text_field_background),
                        cursorColor = colorResource(resource = SharedR.colors.text_secondary)
                    ),
                )

                Row(
                    modifier = modifier.padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "required level",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(resource = SharedR.colors.text_secondary)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Image(
                        modifier = modifier
                            .size(32.dp)
                            .background(
                                color = colorResource(resource = SharedR.colors.background_icon),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(4.dp),
                        painter = painterResource(imageResource = SharedR.images.ic_plus),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
                    )
                    Text(
                        modifier = modifier.padding(start = 16.dp, end = 16.dp),
                        text = "1",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(resource = SharedR.colors.text_secondary)
                    )
                    Image(
                        modifier = modifier
                            .size(32.dp)
                            .background(
                                color = colorResource(resource = SharedR.colors.background_icon),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(4.dp),
                        painter = painterResource(imageResource = SharedR.images.ic_plus),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
                    )
                }
                Spacer(modifier = modifier.weight(1f))

                AppButton(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 70.dp),
                    labelText = "Create",
                    isLoading = false
                ) {

                }

            }
        }
    ) {

    }
}




