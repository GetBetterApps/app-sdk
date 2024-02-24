package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HintSubscriptionSheet(
    modifier: Modifier = Modifier,
    modalSheetState: ModalBottomSheetState,
    text: String,
    onClick: () -> Unit
) {

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 48.dp, top = 16.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                    
                    Row(modifier = modifier.padding(top = 12.dp)) {
                        WeightedSpacer()
                        AppButton(
                            labelText = stringResource(resource = SharedR.strings.paywall_title),
                            isLoading = false,
                            onClick = onClick
                        )
                        WeightedSpacer()
                    }
                }
            }
        }
    ) {}

}