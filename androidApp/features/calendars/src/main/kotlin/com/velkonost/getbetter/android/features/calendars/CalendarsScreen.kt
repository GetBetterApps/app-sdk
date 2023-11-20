package com.velkonost.getbetter.android.features.calendars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.shared.features.calendars.presentation.CalendarsViewModel
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.DateUIItem
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun CalendarsScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarsViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Column {
        LazyRow {
            items(state.datesState.items, key = { it.id }) { item ->
                CalendarDateItem(
                    item = item,
                    isSelected = item.id == state.datesState.selectedDateId
                )
            }
        }

    }
}

@Composable
fun CalendarDateItem(
    modifier: Modifier = Modifier,
    item: DateUIItem,
    isSelected: Boolean
) {

    Column(
        modifier = modifier
            .background(
                color = colorResource(resource = SharedR.colors.background_item),
                shape = MaterialTheme.shapes.medium
            )
            .padding(4.dp)
    ) {
        Text(
            text = "MON",
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(
                resource = if (isSelected) SharedR.colors.text_light
                else SharedR.colors.text_secondary
            )
        )

        Text(
            modifier = modifier.padding(top = 6.dp),
            text = item.date.toString(LocalContext.current),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(
                resource = if (isSelected) SharedR.colors.text_light
                else SharedR.colors.text_secondary
            )
        )
    }

}