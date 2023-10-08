package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.velkonost.getbetter.core.compose.components.AppAlertDialog
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SubmitDialogs(
    isDeleteAreaDialogVisible: MutableState<Boolean>,
    isLeaveAreaDialogVisible: MutableState<Boolean>,
    onConfirmDelete: () -> Unit,
    onConfirmLeave: () -> Unit
) {
    if (isDeleteAreaDialogVisible.value) {
        AppAlertDialog(
            title = stringResource(resource = SharedR.strings.add_area_confirm_delete_title),
            text = stringResource(resource = SharedR.strings.add_area_confirm_delete_text),
            confirmTitle = stringResource(resource = SharedR.strings.confirm),
            cancelTitle = stringResource(resource = SharedR.strings.cancel),
            onDismiss = { isDeleteAreaDialogVisible.value = false },
            onConfirmClick = {
                onConfirmDelete()
                isDeleteAreaDialogVisible.value = false
            }
        )
    }

    if (isLeaveAreaDialogVisible.value) {
        AppAlertDialog(
            title = stringResource(resource = SharedR.strings.add_area_confirm_leave_title),
            text = stringResource(resource = SharedR.strings.add_area_confirm_leave_text),
            confirmTitle = stringResource(resource = SharedR.strings.confirm),
            cancelTitle = stringResource(resource = SharedR.strings.cancel),
            onDismiss = { isLeaveAreaDialogVisible.value = false },
            onConfirmClick = {
                onConfirmLeave()
                isLeaveAreaDialogVisible.value = false
            }
        )
    }

}