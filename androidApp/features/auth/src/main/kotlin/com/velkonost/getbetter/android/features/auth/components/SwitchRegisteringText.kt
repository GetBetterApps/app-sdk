package com.velkonost.getbetter.android.features.auth.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ColumnScope.SwitchRegisteringText(
    modifier: Modifier,
    targetState: Boolean,
    haptic: HapticFeedback,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    AnimatedContent(
        targetState = targetState,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 500)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 500))
        },
        contentAlignment = Alignment.Center, label = ""
    ) { isRegistering ->
        Text(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(top = 12.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick.invoke()
                },
            text = stringResource(
                resource =
                if (isRegistering) SharedR.strings.auth_have_account
                else SharedR.strings.auth_dont_have_account
            ).uppercase(),
            color = colorResource(resource = SharedR.colors.text_light).copy(alpha = 0.5f),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.End
        )

    }
}