package com.velkonost.getbetter.android.features.taskdetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun AbilityData(
    modifier: Modifier = Modifier,
    item: Ability,
    onClick: (Ability) -> Unit
) {
    val context = LocalContext.current

    PrimaryBox(
        modifier = modifier.padding(top = 6.dp)
    ) {
        Column(
            modifier = modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick(item) }
        ) {
            Row {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )
                Spacer(modifier.weight(1f))
                Text(
                    text = item.experienceData.currentLevelStr.toString(context),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )
            }

            LinearProgressIndicator(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                progress = item.experienceData.remainExperiencePercent,
                color = colorResource(resource = SharedR.colors.button_gradient_start),
                trackColor = colorResource(resource = SharedR.colors.text_unimportant_color),
                strokeCap = StrokeCap.Round
            )
        }
    }


}