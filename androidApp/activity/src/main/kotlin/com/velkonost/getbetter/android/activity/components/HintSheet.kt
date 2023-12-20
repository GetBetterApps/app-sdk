package com.velkonost.getbetter.android.activity.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HintSheet(
    modifier: Modifier = Modifier,
    state: MessageType.Sheet?,
    modalSheetState: ModalBottomSheetState,
) {

    val context = LocalContext.current

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column {
                    if (state?.title != null) {
                        Text(
                            text = state.title!!.toString(context),
                            style = MaterialTheme.typography.titleMedium,
                            color = colorResource(resource = SharedR.colors.text_title)
                        )
                    }

                    if (state?.text != null) {
                        Text(
                            text = state.text!!.toString(context),
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorResource(resource = SharedR.colors.text_title)
                        )
                    }
                }
            }
        }
    ) {}

}