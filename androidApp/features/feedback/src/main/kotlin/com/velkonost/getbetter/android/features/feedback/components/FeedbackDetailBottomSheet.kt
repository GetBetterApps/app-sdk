package com.velkonost.getbetter.android.features.feedback.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.core.model.feedback.Feedback
import com.velkonost.getbetter.shared.core.model.feedback.FeedbackMessage
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackDetailsState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedbackDetailBottomSheet(
    modifier: Modifier = Modifier,
    modalSheetState: ModalBottomSheetState,
    item: Feedback,
    feedbackDetailsState: FeedbackDetailsState
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
                        .padding(horizontal = 4.dp)
                        .verticalScroll(scrollState)
                ) {
                    Spacer(modifier.height(32.dp))

                    item.messages.forEach { message ->

                    }
                }
            }
        }
    ) {}
}

@Composable
fun FeedbackMessageView(
    modifier: Modifier = Modifier,
    message: FeedbackMessage
) {

}