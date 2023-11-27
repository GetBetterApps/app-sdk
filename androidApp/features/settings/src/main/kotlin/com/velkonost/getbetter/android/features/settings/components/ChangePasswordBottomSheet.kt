package com.velkonost.getbetter.android.features.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.SingleLineTextField
import com.velkonost.getbetter.shared.features.settings.presentation.contract.ChangePasswordState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChangePasswordBottomSheet(
    modifier: Modifier = Modifier,
    changePasswordState: ChangePasswordState,
    modalSheetState: ModalBottomSheetState,
    onOldPasswordChanged: (String) -> Unit,
    onNewPasswordChanged: (String) -> Unit,
    onRepeatedNewPasswordChanged: (String) -> Unit,
    onChangedClick: () -> Unit
) {
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
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier.height(32.dp))

                    SingleLineTextField(
                        paddingValues = PaddingValues(top = 12.dp),
                        value = changePasswordState.oldPassword,
                        placeholderText = "old pass",
                        onValueChanged = onOldPasswordChanged,
                    )

                    SingleLineTextField(
                        paddingValues = PaddingValues(top = 12.dp),
                        value = changePasswordState.newPassword,
                        placeholderText = "new pass",
                        onValueChanged = onNewPasswordChanged,
                    )

                    SingleLineTextField(
                        paddingValues = PaddingValues(top = 12.dp),
                        value = changePasswordState.repeatedNewPassword,
                        placeholderText = "repeat new pass",
                        onValueChanged = onRepeatedNewPasswordChanged,
                    )

                    Spacer(modifier.weight(1f))

                    AppButton(
//                        modifier = modifier.padding(bottom = 64.dp),
                        labelText = "change",
                        isLoading = false,
                        onClick = onChangedClick
                    )


                }
            }

        }
    ) {}
}