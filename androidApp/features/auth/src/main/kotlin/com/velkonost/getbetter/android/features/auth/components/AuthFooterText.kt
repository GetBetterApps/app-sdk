package com.velkonost.getbetter.android.features.auth.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AuthFooterText(
    modifier: Modifier
) {
    Text(
        modifier = modifier.padding(bottom = 48.dp, start = 32.dp, end = 32.dp),
        text = stringResource(resource = SharedR.strings.auth_footer_text),
        color = colorResource(resource = SharedR.colors.text_light).copy(alpha = 0.2f),
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center
    )
}