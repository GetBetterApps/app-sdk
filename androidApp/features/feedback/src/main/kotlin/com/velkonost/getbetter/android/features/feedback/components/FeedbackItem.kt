package com.velkonost.getbetter.android.features.feedback.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.feedback.Feedback
import com.velkonost.getbetter.shared.core.model.feedback.FeedbackStatus
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun FeedbackItem(
    modifier: Modifier = Modifier,
    item: Feedback,
    onClick: () -> Unit
) {

    PrimaryBox {
        Column(
            modifier = modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier
                        .background(
                            color = colorResource(resource = SharedR.colors.background_icon),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 4.dp, horizontal = 6.dp),
                    text = item.type.uiContent.toString(LocalContext.current),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )
                Spacer(modifier.weight(1f))

                Box(
                    modifier
                        .size(12.dp)
                        .background(
                            color = colorResource(
                                resource =
                                if (item.status == FeedbackStatus.Opened) SharedR.colors.green
                                else SharedR.colors.red
                            ),
                            shape = CircleShape
                        )
                )
            }

            Text(
                modifier = modifier.padding(top = 12.dp),
                text = item.messages.first().text,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(resource = SharedR.colors.text_primary),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}