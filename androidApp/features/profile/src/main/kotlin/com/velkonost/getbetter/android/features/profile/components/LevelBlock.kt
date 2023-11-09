package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.user.ExperienceData
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun LevelBlock(
    modifier: Modifier = Modifier,
    experienceData: ExperienceData
) {

    PrimaryBox(
        modifier = modifier.padding(top = 20.dp)
    ) {
        Column {
            Row {
                Spacer(modifier.weight(1f))
                Text(
                    text = "Level ${experienceData.currentLevel}",
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )
            }

            LinearProgressIndicator(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                progress = experienceData.remainExperiencePercent,
                color = colorResource(resource = SharedR.colors.button_gradient_start),
                trackColor = colorResource(resource = SharedR.colors.text_unimportant_color),
                strokeCap = StrokeCap.Round
            )
        }
    }

}