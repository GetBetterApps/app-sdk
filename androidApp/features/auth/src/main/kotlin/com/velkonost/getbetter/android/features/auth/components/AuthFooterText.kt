package com.velkonost.getbetter.android.features.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AuthFooterText(
    modifier: Modifier,
    onPrivacyClick: () -> Unit,
    onTermsClick: () -> Unit
) {

    Row {
        Spacer(modifier.weight(1f))
        Text(
            modifier = modifier.padding(start = 32.dp, end = 32.dp),
            text = stringResource(resource = SharedR.strings.auth_footer_text),
            color = colorResource(resource = SharedR.colors.text_light).copy(alpha = 0.5f),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier.weight(1f))
    }

    Row(modifier = modifier.padding(bottom = 48.dp)) {
        Spacer(modifier.weight(1f))
        Text(
            modifier = modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onPrivacyClick
            ),
            text = stringResource(resource = SharedR.strings.profile_privacy_title),
            color = colorResource(resource = SharedR.colors.text_light).copy(alpha = 0.5f),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier.width(12.dp))
        Text(
            modifier = modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onTermsClick
            ),
            text = stringResource(resource = SharedR.strings.profile_terms_title),
            color = colorResource(resource = SharedR.colors.text_light).copy(alpha = 0.5f),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier.weight(1f))
    }
}