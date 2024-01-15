package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun AppAlertDialog(
    title: String,
    text: String,
    confirmTitle: String,
    cancelTitle: String,
    onDismiss: () -> Unit,
    onConfirmClick: () -> Unit,
    onCancelClick: () -> Unit = onDismiss
) {
    AlertDialog(
        title = {
            Text(
                text = title,
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = text,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                modifier = Modifier.width(96.dp),
                onClick = onConfirmClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(resource = SharedR.colors.button_gradient_start)
                )
            ) {
                Text(
                    text = confirmTitle,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    color = colorResource(resource = SharedR.colors.text_light)
                )
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier.width(96.dp),
                onClick = onCancelClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(resource = SharedR.colors.button_gradient_start)
                )
            ) {
                Text(
                    text = cancelTitle,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    color = colorResource(resource = SharedR.colors.text_light)
                )
            }
        },
        contentColor = colorResource(resource = SharedR.colors.text_title),
        backgroundColor = colorResource(resource = SharedR.colors.background_item)
    )
}