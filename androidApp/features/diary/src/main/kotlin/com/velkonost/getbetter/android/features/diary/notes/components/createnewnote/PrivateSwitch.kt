package com.velkonost.getbetter.android.features.diary.notes.components.createnewnote

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PrivateSwitch(
    modifier: Modifier = Modifier,
    isPrivate: Boolean,
    isEnable: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    PrimaryBox(padding = 0) {
        Row(
            modifier = modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.padding(start = 16.dp),
                text = stringResource(
                    resource = if (isPrivate) SharedR.strings.private_state
                    else SharedR.strings.public_state
                ),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
            Spacer(modifier = modifier.weight(1f))
            Switch(
                modifier = modifier.padding(end = 16.dp),
                enabled = isEnable,
                checked = isPrivate,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = colorResource(resource = SharedR.colors.icon_active),
                    checkedThumbColor = colorResource(resource = SharedR.colors.background_icon),
                    uncheckedTrackColor = colorResource(resource = SharedR.colors.icon_inactive),
                    uncheckedThumbColor = colorResource(resource = SharedR.colors.background_icon)
                )
            )
        }
    }
}