package com.velkonost.getbetter.android.features.feedback.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.shared.core.model.feedback.FeedbackType
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.NewFeedbackState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateNewFeedbackBottomSheet(
    modifier: Modifier = Modifier,
    newFeedbackState: NewFeedbackState,
    modalSheetState: ModalBottomSheetState,
    onTypeChanged: (FeedbackType) -> Unit,
    onTextChanged: (String) -> Unit,
    onCreateClick: () -> Unit
) {
    val scrollState = rememberScrollState()


    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            if (newFeedbackState.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(20.dp)
                ) {
                    Loader(modifier = modifier.align(Alignment.Center))
                }
            } else {
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
                            .padding(horizontal = 4.dp)
                            .verticalScroll(scrollState)
                    ) {
                        Spacer(modifier.height(32.dp))

                        Row {
                            FeedbackTypeView(
                                feedbackType = FeedbackType.Feature,
                                selected = newFeedbackState.type == FeedbackType.Feature,
                                onClick = {
                                    onTypeChanged.invoke(FeedbackType.Feature)
                                }
                            )

                            FeedbackTypeView(
                                feedbackType = FeedbackType.Report,
                                selected = newFeedbackState.type == FeedbackType.Report,
                                onClick = {
                                    onTypeChanged.invoke(FeedbackType.Report)
                                }
                            )
                        }

                        MultilineTextField(
                            value = newFeedbackState.text,
                            placeholderText = stringResource(
                                resource =
                                if (newFeedbackState.type == FeedbackType.Feature) SharedR.strings.support_feature_hint
                                else SharedR.strings.support_report_hint
                            ),
                            onValueChanged = onTextChanged,
                            paddingValues = PaddingValues(top = 12.dp, start = 6.dp, end = 6.dp)
                        )
                    }

                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = modifier.weight(1f))

                        Box(
                            modifier = modifier
                                .height(30.dp)
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            colorResource(resource = SharedR.colors.main_background)
                                                .copy(alpha = 0.7f),
                                            colorResource(resource = SharedR.colors.main_background),
                                        ),
                                    ),
                                )
                        )

                        AppButton(
                            modifier = modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 70.dp),
                            labelText = stringResource(
                                resource = SharedR.strings.diary_areas_create_button
                            ),
                            isLoading = false,
                            onClick = onCreateClick
                        )
                    }
                }
            }
        }
    ) {

    }
}

@Composable
fun RowScope.FeedbackTypeView(
    modifier: Modifier = Modifier,
    feedbackType: FeedbackType,
    selected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Text(
        modifier = modifier
            .weight(1f)
            .padding(horizontal = 6.dp)
            .background(
                color = colorResource(
                    resource =
                    if (selected) SharedR.colors.button_gradient_start
                    else SharedR.colors.background_item
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 10.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        text = feedbackType.uiContent.toString(LocalContext.current),
        style = MaterialTheme.typography.labelMedium,
        color = colorResource(
            resource =
            if (selected) SharedR.colors.text_light
            else SharedR.colors.text_primary
        ),
        textAlign = TextAlign.Center
    )
}