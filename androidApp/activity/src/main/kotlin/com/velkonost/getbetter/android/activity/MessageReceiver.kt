package com.velkonost.getbetter.android.activity

import android.content.Context
import android.widget.Toast
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.MutableState
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageDeque
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
suspend fun onMessageReceived(
    message: Message,
    hintSheet: MutableState<MessageType.Sheet?>,
    hintSheetState: ModalBottomSheetState,
    snackBarHostState: SnackbarHostState,
    context: Context
) {

    when (val component = message.messageType) {
        is MessageType.Sheet -> {
            if (!hintSheetState.isVisible) {
                hintSheet.value = component
                hintSheetState.show()
            }
            MessageDeque.dequeue()
        }

        is MessageType.SnackBar -> {
            val text = message.text ?: message.textResource?.toString(context = context) ?: ""
            val result = snackBarHostState.showSnackbar(
                message = text,
                actionLabel = component.actionLabel,
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )

            when (result) {
                SnackbarResult.Dismissed -> {
                    component.onDismiss.invoke()
                    MessageDeque.dequeue()
                }

                SnackbarResult.ActionPerformed -> {
                    component.onAction.invoke()
                    MessageDeque.dequeue()
                }
            }

        }

        is MessageType.Toast -> {
            Toast.makeText(context, message.text, Toast.LENGTH_LONG).show()

            delay(timeMillis = 4_000)
            MessageDeque.dequeue()
        }

        else -> {
            // Ignore this block
            // MessageType could be .None
            // or message could be null
        }
    }
}