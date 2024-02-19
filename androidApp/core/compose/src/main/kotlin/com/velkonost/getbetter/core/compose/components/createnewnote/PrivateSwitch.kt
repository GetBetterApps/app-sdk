package com.velkonost.getbetter.core.compose.components.createnewnote

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_60
import com.velkonost.getbetter.core.compose.theme.Pixel.PX_ZERO
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
    val viewHeight = remember { DP_60 }
    val viewPadding = remember { PX_ZERO }
    val horizontalPadding = remember { DP_16 }

    PrimaryBox(padding = viewPadding) {
        Row(
            modifier = modifier.height(viewHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.padding(start = horizontalPadding),
                text = stringResource(
                    resource = if (isPrivate) SharedR.strings.private_state
                    else SharedR.strings.public_state
                ),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )
            WeightedSpacer()
            Switch(
                modifier = modifier.padding(end = horizontalPadding),
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