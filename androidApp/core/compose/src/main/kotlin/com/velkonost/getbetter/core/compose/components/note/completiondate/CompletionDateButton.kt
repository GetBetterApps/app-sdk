package com.velkonost.getbetter.core.compose.components.note.completiondate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource

@Composable
internal fun CompletionDateButton(
    label: String?,
    isLoading: Boolean,
    onCompleteClick: (() -> Unit),
    modifier: Modifier = Modifier
) {

    val viewPadding = remember { DP_16 }

    AnimatedVisibility(visible = label == null) {
        Row(
            modifier = modifier
                .padding(horizontal = viewPadding)
                .padding(bottom = viewPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeightedSpacer()

            AppButton(
                labelText = stringResource(resource = SharedR.strings.note_detail_complete_goal_text),
                isLoading = isLoading,
                onClick = onCompleteClick
            )

            WeightedSpacer()
        }
    }
}