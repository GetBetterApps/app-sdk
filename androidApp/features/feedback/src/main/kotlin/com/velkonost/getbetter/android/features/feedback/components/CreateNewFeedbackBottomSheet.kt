package com.velkonost.getbetter.android.features.feedback.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.shared.core.model.feedback.FeedbackType
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.NewFeedbackState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateNewFeedbackBottomSheet(
    modifier: Modifier = Modifier,
    newFeedbackState: NewFeedbackState,
    modalSheetState: ModalBottomSheetState,
    onTypeChanged: (FeedbackType) -> Unit,
    onTextChanged: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val interactionSource = remember { MutableInteractionSource() }

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
                        .padding(horizontal = 16.dp)
                        .verticalScroll(scrollState)
                ) {
                    Spacer(modifier.height(32.dp))

                    Row {
                        Text(
                            modifier = modifier
                                .weight(1f)
                                .padding(end = 6.dp)
                                .background(
                                    color = colorResource(
                                        resource =
                                        if (newFeedbackState.type == FeedbackType.Feature) SharedR.colors.button_gradient_start
                                        else SharedR.colors.background_item
                                    ),
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(vertical = 6.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { onTypeChanged.invoke(FeedbackType.Feature) },
                            text = "Feature",
                            style = MaterialTheme.typography.labelMedium,
                            color = colorResource(
                                resource =
                                if (newFeedbackState.type == FeedbackType.Feature) SharedR.colors.text_light
                                else SharedR.colors.text_primary
                            ),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = modifier
                                .weight(1f)
                                .padding(start = 6.dp)
                                .background(
                                    color = colorResource(
                                        resource =
                                        if (newFeedbackState.type == FeedbackType.Report) SharedR.colors.button_gradient_start
                                        else SharedR.colors.background_item
                                    ),
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(vertical = 6.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { onTypeChanged.invoke(FeedbackType.Report) },
                            text = "Report",
                            style = MaterialTheme.typography.labelMedium,
                            color = colorResource(
                                resource =
                                if (newFeedbackState.type == FeedbackType.Report) SharedR.colors.text_light
                                else SharedR.colors.text_primary
                            ),
                            textAlign =
                            TextAlign.Center
                        )
                    }

                    MultilineTextField(
                        value = newFeedbackState.text,
                        placeholderText = "123",
                        onValueChanged = onTextChanged
                    )
                }
            }
        }
    ) {

    }

}