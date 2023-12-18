package com.velkonost.getbetter.android.features.onboarding.components

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OnboardingAbilityItem(
    modifier: Modifier = Modifier,
    item: Ability
) {

    Box(modifier = modifier.padding(horizontal = 8.dp)) {
        PrimaryBox {
            Column {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(resource = SharedR.colors.text_light)
                )

                Text(
                    modifier = modifier.padding(top = 12.dp),
                    text = item.description,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(SharedR.colors.text_primary)
                )

                Row(modifier = modifier.padding(top = 12.dp)) {
                    Text(
                        text = stringResource(resource = SharedR.strings.experience_your_title),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )
                    Spacer(modifier.weight(1f))
                    Text(
                        text = item.experienceData.currentLevelStr.toString(LocalContext.current),
                        style = MaterialTheme.typography.bodyMedium,
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


}