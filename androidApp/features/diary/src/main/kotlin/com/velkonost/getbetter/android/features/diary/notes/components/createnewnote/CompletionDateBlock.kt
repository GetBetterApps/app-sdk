package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppDatePickerDialog
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun CompletionDateBlock(
    modifier: Modifier = Modifier
) {

    var date by remember { mutableStateOf("Not Set") }
    var showDatePicker by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    PrimaryBox(padding = 0) {
        Row(
            modifier = modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.padding(start = 12.dp),
                text = "Completion date",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
            Spacer(modifier = modifier.weight(1f))

            Text(
                modifier = modifier
                    .padding(end = 12.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.text_field_background),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(12.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        showDatePicker = true
                    },
                text = date,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary),
            )
        }
    }

    if (showDatePicker) {
        AppDatePickerDialog(
            title = "123",
            onConfirm = { millis, selectedDate ->
                showDatePicker = false
                date = selectedDate
            },
            onDecline = {
                showDatePicker = false
            }
        )
    }
}

