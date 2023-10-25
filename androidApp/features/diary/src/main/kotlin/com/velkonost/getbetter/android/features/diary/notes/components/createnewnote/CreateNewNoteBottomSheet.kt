package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.notes.components.createnewnote.areapicker.AreaPicker
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import model.Area

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun CreateNewNoteBottomSheet(
    modifier: Modifier = Modifier,
    areas: List<Area>,
    state: CreateNewNoteViewState,
    modalSheetState: ModalBottomSheetState,
    onAreaSelect: (Area) -> Unit,
    onAreasError: () -> Unit
) {

    val isAreaPickerVisible = remember { mutableStateOf(false) }

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

                    AreaPicker(
                        areas = areas,
                        selectedArea = state.selectedArea,
                        isAreaPickerVisible = isAreaPickerVisible,
                        onAreaSelect = onAreaSelect
                    )
                }
            }
        }
    ) {

    }
}