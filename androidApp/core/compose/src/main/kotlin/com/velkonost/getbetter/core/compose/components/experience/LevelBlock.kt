package com.velkonost.getbetter.core.compose.components.experience

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.Dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_20
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_8
import com.velkonost.getbetter.shared.core.model.user.ExperienceData
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun LevelBlock(
    modifier: Modifier = Modifier,
    topPadding: Dp = DP_20,
    experienceData: ExperienceData,
    isOwn: Boolean = true
) {

    val indicatorTopPadding = remember { DP_8 }
    val context = LocalContext.current

    PrimaryBox(modifier = modifier.padding(top = topPadding)) {
        Column {
            Row {
                Text(
                    text = stringResource(
                        resource =
                        if (isOwn) SharedR.strings.experience_your_title
                        else SharedR.strings.experience_user_title
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )
                WeightedSpacer()
                Text(
                    text = experienceData.currentLevelStr.toString(context),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )
            }

            LinearProgressIndicator(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = indicatorTopPadding),
                progress = experienceData.remainExperiencePercent,
                color = colorResource(resource = SharedR.colors.button_gradient_start),
                trackColor = colorResource(resource = SharedR.colors.text_unimportant_color),
                strokeCap = StrokeCap.Round
            )
        }
    }

}