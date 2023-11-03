package com.velkonost.getbetter.core.compose.components.note.completiondate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource

@Composable
internal fun CompletionDateButton(
    label: String?,
    isLoading: Boolean,
    onCompleteClick: (() -> Unit),
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = label == null) {
        Row(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier.weight(1f))

            AppButton(
                labelText = stringResource(resource = SharedR.strings.note_detail_complete_goal_text),
                isLoading = isLoading,
                onClick = onCompleteClick
            )

            Spacer(modifier.weight(1f))
        }
    }
}