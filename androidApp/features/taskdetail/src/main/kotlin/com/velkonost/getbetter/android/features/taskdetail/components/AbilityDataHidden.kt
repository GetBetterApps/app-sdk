package com.velkonost.getbetter.android.features.taskdetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AbilityDataHidden(
    modifier: Modifier = Modifier
) {
    PrimaryBox {
        Column {
            Row {
                Spacer(modifier.weight(1f))
                Text(
                    text = stringResource(resource = SharedR.strings.task_ability_hidden),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )
                Spacer(modifier.weight(1f))
            }
        }
    }
}