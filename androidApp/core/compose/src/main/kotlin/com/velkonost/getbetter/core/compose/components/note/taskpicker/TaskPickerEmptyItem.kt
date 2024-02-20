package com.velkonost.getbetter.core.compose.components.note.taskpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_12
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_2
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ColumnScope.TaskPickerEmptyItem(
    modifier: Modifier = Modifier
) {

    val viewMargin = remember { DP_2 }
    val textTopPadding = remember { DP_12 }
    val viewInnerPadding = remember { DP_16 }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(horizontal = viewMargin)
            .background(
                color = colorResource(resource = SharedR.colors.text_field_background),
                shape = MaterialTheme.shapes.medium
            )
            .padding(viewInnerPadding)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = modifier.padding(top = textTopPadding),
                text = stringResource(resource = SharedR.strings.create_note_without_task),
                color = colorResource(resource = SharedR.colors.text_regular_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}