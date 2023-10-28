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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppDatePickerDialog
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
fun CompletionDateBlock(
    modifier: Modifier = Modifier
) {


    val context = LocalContext.current
    var date by remember {
        mutableStateOf(
            StringDesc.Resource(SharedR.strings.create_note_completion_date_hint).toString(context)
        )
    }
    var showDatePicker by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    PrimaryBox(padding = 0) {
        Row(
            modifier = modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.padding(start = 16.dp),
                text = stringResource(resource = SharedR.strings.create_note_completion_date_title),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
            Spacer(modifier = modifier.weight(1f))

            Text(
                modifier = modifier
                    .padding(end = 16.dp)
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
            title = stringResource(resource = SharedR.strings.create_note_completion_date_title),
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

