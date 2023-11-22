package com.velkonost.getbetter.android.features.calendars.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.DateUIItem
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun CalendarDateItem(
    modifier: Modifier = Modifier,
    item: DateUIItem,
    isSelected: Boolean,
    onClick: (Long) -> Unit
) {

    Column(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.small,
            )
            .background(
                color = colorResource(
                    resource = if (isSelected) SharedR.colors.button_gradient_start
                    else SharedR.colors.background_item
                ), shape = MaterialTheme.shapes.small
            )
            .size(52.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) { onClick.invoke(item.id) },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.dayOfWeek.toString(LocalContext.current).uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(
                resource = if (isSelected) SharedR.colors.text_light
                else SharedR.colors.text_primary
            )
        )

        Text(
            text = item.day.toString(LocalContext.current),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(
                resource = if (isSelected) SharedR.colors.text_light
                else SharedR.colors.text_primary
            )
        )
    }
}