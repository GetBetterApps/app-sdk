package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.velkonost.getbetter.core.compose.components.AppAlertDialog

@Composable
fun SubmitDialogs(
    isDeleteAreaDialogVisible: MutableState<Boolean>,
    isLeaveAreaDialogVisible: MutableState<Boolean>,
    onConfirmDelete: () -> Unit,
    onConfirmLeave: () -> Unit
) {
    if (isDeleteAreaDialogVisible.value) {
        AppAlertDialog(
            title = "Are you sure?",
            text = "Delete area",
            confirmTitle = "Delete",
            cancelTitle = "Cancel",
            onDismiss = { isDeleteAreaDialogVisible.value = false },
            onConfirmClick = {
                onConfirmDelete()
                isDeleteAreaDialogVisible.value = false
            }
        )
    }

    if (isLeaveAreaDialogVisible.value) {
        AppAlertDialog(
            title = "Are you sure?",
            text = "leave area",
            confirmTitle = "Leave",
            cancelTitle = "Cancel",
            onDismiss = { isLeaveAreaDialogVisible.value = false },
            onConfirmClick = {
                onConfirmLeave()
                isLeaveAreaDialogVisible.value = false
            }
        )
    }

}