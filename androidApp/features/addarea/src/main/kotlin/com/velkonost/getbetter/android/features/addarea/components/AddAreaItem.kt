package com.velkonost.getbetter.android.features.addarea.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.DisabledAppButton
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.TermsOfMembership
import com.velkonost.getbetter.shared.features.addarea.presentation.model.AreaUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AddAreaItem(
    modifier: Modifier = Modifier,
    item: AreaUI,
    onAddAreaClick: (String) -> Unit
) {

    val haptic = LocalHapticFeedback.current

    PrimaryBox(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
//                onClick.invoke("")
            }
    ) {
        Column {
            Row {
                Image(
                    modifier = modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(imageResource = item.emojiRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = item.name,
                        color = colorResource(resource = SharedR.colors.text_title),
                        style = MaterialTheme.typography.labelLarge
                    )

                    Text(
                        modifier = modifier.padding(top = 4.dp),
                        text = item.description,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = colorResource(resource = SharedR.colors.text_secondary),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Row(
                modifier = modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp)
                        .background(
                            color = colorResource(resource = SharedR.colors.background_icon),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(4.dp)
                ) {
                    Text(
                        modifier = modifier.align(Alignment.Center),
                        text = "14000\nmembers",
                        color = colorResource(resource = SharedR.colors.text_secondary),
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                }

                Box(
                    modifier = modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp)
                        .background(
                            color = colorResource(resource = SharedR.colors.background_icon),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(4.dp)
                ) {
                    Text(
                        modifier = modifier
                            .align(Alignment.Center),
                        text = "20200\nnotes",
                        color = colorResource(resource = SharedR.colors.text_secondary),
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                }

                Box(
                    modifier = modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp)
                        .background(
                            color = colorResource(resource = SharedR.colors.background_icon),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(4.dp)
                ) {
                    Text(
                        modifier = modifier
                            .align(Alignment.Center),
                        text = "20200\ntasks",
                        color = colorResource(resource = SharedR.colors.text_secondary),
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (item.termsOfMembership == TermsOfMembership.Allow) {
                AppButton(
                    modifier = modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    labelText = stringResource(resource = SharedR.strings.add_area_add_area_button),
                    isLoading = item.isLoading
                ) { onAddAreaClick(item.id) }
            } else {
                DisabledAppButton(
                    modifier = modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    labelText = stringResource(
                        resource =
                        when (item.termsOfMembership) {
                            TermsOfMembership.AlreadyJoined -> SharedR.strings.add_area_already_added_button
                            else -> SharedR.strings.add_area_low_level_button
                        }
                    )
                )
            }
        }
    }
}