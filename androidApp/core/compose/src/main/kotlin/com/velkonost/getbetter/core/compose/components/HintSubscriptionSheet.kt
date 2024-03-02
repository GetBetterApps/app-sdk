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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HintSubscriptionSheet(
    modifier: Modifier = Modifier,
    modalSheetState: ModalBottomSheetState,
    text: String,
    onClick: () -> Unit
) {

    val scope = rememberCoroutineScope()

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
                    .padding(bottom = 56.dp, top = 16.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Row {
                        WeightedSpacer()
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorResource(resource = SharedR.colors.text_title),
                            textAlign = TextAlign.Center
                        )
                        WeightedSpacer()
                    }


                    Row(modifier = modifier.padding(top = 24.dp)) {
                        WeightedSpacer()
                        AppButton(
                            labelText = stringResource(resource = SharedR.strings.profile_sub_upgrade),
                            isLoading = false,
                            onClick = {
                                onClick()
                                scope.launch {
                                    modalSheetState.hide()
                                }
                            }
                        )
                        WeightedSpacer()
                    }
                }
            }
        }
    ) {}

}