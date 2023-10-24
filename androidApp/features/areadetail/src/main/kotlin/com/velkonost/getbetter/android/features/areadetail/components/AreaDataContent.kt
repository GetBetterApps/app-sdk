package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource


@Composable
internal fun AreaDataContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .weight(1f)
                .padding(end = 4.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
        ) {
            Text(
                modifier = modifier.align(Alignment.Center),
                text = "14000\nmembers",
                color = colorResource(resource = SharedR.colors.text_secondary),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = modifier
                .weight(1f)
                .padding(start = 4.dp, end = 4.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = "20200\nnotes",
                color = colorResource(resource = SharedR.colors.text_secondary),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = modifier
                .weight(1f)
                .padding(start = 4.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = "20200\ntasks",
                color = colorResource(resource = SharedR.colors.text_secondary),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}