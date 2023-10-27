package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AppDatePickerDialog(
    title: String,
    onConfirm: (Long?, String) -> Unit,
    onDecline: () -> Unit
) {

    val datePickerState = rememberDatePickerState()

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    DatePickerDialog(
        shape = MaterialTheme.shapes.medium,
        onDismissRequest = onDecline,
        confirmButton = {
            Button(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.width(96.dp),
                onClick = {
                    onConfirm(datePickerState.selectedDateMillis, selectedDate)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(resource = SharedR.colors.background_item)
                )
            ) {
                Text(
                    text = stringResource(resource = SharedR.strings.confirm),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(resource = SharedR.colors.text_light)
                )
            }
        },
        dismissButton = {
            Button(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.width(96.dp),
                onClick = onDecline,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(resource = SharedR.colors.background_item)
                )
            ) {
                Text(
                    text = stringResource(resource = SharedR.strings.cancel),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(resource = SharedR.colors.text_light)
                )
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = colorResource(resource = SharedR.colors.button_gradient_start),
            titleContentColor = colorResource(resource = SharedR.colors.text_title),
            headlineContentColor = colorResource(resource = SharedR.colors.text_title),
            weekdayContentColor = colorResource(resource = SharedR.colors.text_title),
            subheadContentColor = colorResource(resource = SharedR.colors.text_light),
            yearContentColor = colorResource(resource = SharedR.colors.text_title),
            currentYearContentColor = colorResource(resource = SharedR.colors.text_title),
            selectedYearContentColor = colorResource(resource = SharedR.colors.onboarding_background_gradient_start),
            selectedYearContainerColor = colorResource(resource = SharedR.colors.main_background),
            dayContentColor = colorResource(resource = SharedR.colors.text_title),
            disabledDayContentColor = colorResource(resource = SharedR.colors.text_primary),
            disabledSelectedDayContentColor = colorResource(resource = SharedR.colors.text_primary),
            disabledSelectedDayContainerColor = colorResource(resource = SharedR.colors.text_primary),
            selectedDayContentColor = colorResource(resource = SharedR.colors.button_gradient_start),
            selectedDayContainerColor = colorResource(resource = SharedR.colors.main_background),
            todayContentColor = colorResource(resource = SharedR.colors.text_primary),
            todayDateBorderColor = colorResource(resource = SharedR.colors.text_primary),
            dayInSelectionRangeContentColor = colorResource(resource = SharedR.colors.text_primary),
            dayInSelectionRangeContainerColor = colorResource(resource = SharedR.colors.text_primary),
        )
    ) {
        DatePicker(
            title = {
                Text(
                    modifier = Modifier.padding(
                        PaddingValues(
                            start = 24.dp,
                            end = 12.dp,
                            top = 16.dp
                        )
                    ),
                    text = title,
                )
            },
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                containerColor = colorResource(resource = SharedR.colors.button_gradient_start),
                titleContentColor = colorResource(resource = SharedR.colors.text_title),
                headlineContentColor = colorResource(resource = SharedR.colors.text_title),
                weekdayContentColor = colorResource(resource = SharedR.colors.text_title),
                subheadContentColor = colorResource(resource = SharedR.colors.text_title),
                yearContentColor = colorResource(resource = SharedR.colors.text_title),
                currentYearContentColor = colorResource(resource = SharedR.colors.text_title),
                selectedYearContentColor = colorResource(resource = SharedR.colors.onboarding_background_gradient_start),
                selectedYearContainerColor = colorResource(resource = SharedR.colors.main_background),
                dayContentColor = colorResource(resource = SharedR.colors.text_title),
                disabledDayContentColor = colorResource(resource = SharedR.colors.text_primary),
                disabledSelectedDayContentColor = colorResource(resource = SharedR.colors.text_primary),
                disabledSelectedDayContainerColor = colorResource(resource = SharedR.colors.text_primary),
                selectedDayContentColor = colorResource(resource = SharedR.colors.button_gradient_start),
                selectedDayContainerColor = colorResource(resource = SharedR.colors.main_background),
                todayContentColor = colorResource(resource = SharedR.colors.text_primary),
                todayDateBorderColor = colorResource(resource = SharedR.colors.text_primary),
                dayInSelectionRangeContentColor = colorResource(resource = SharedR.colors.text_primary),
                dayInSelectionRangeContainerColor = colorResource(resource = SharedR.colors.text_primary),
            )
        )
    }

}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}