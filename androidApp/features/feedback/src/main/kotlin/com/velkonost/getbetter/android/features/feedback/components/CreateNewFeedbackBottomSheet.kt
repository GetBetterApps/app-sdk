package com.velkonost.getbetter.android.features.feedback.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.NewFeedbackState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateNewFeedbackBottomSheet(
    modifier: Modifier = Modifier,
    newFeedbackState: NewFeedbackState,
    modalSheetState: ModalBottomSheetState
) {
    val scrollState = rememberScrollState()

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(bottom = 40.dp)
                        .verticalScroll(scrollState)
                ) {
                    Row {

                        Text(
                            modifier = modifier
                                .weight(1f)
                                .background(
                                    color = colorResource(resource = SharedR.colors.background_item),
                                    shape = MaterialTheme.shapes.medium
                                )
                                .padding(end = 6.dp),
                            text = "Feature",
                            style = MaterialTheme.typography.labelMedium,
                            color = colorResource(resource = SharedR.colors.text_primary)
                        )

                        Text(
                            modifier = modifier
                                .weight(1f)
                                .background(
                                    color = colorResource(resource = SharedR.colors.background_item),
                                    shape = MaterialTheme.shapes.medium
                                )
                                .padding(start = 6.dp),
                            text = "Report",
                            style = MaterialTheme.typography.labelMedium,
                            color = colorResource(resource = SharedR.colors.text_primary)
                        )

                        MultilineTextField(
                            value = newFeedbackState.text,
                            placeholderText = "123",
                            onValueChanged = {

                            }
                        )
                    }
                }
            }
        }
    ) {

    }

}