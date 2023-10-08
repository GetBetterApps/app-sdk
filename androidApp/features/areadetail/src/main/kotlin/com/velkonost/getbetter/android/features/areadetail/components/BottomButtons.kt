package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BottomButtons(
    modifier: Modifier = Modifier,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    onLeaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column {
        Spacer(modifier = modifier.weight(1f))

        Row {
            AnimatedVisibility(
                modifier = modifier
                    .weight(1f),
                visible = !isEditing
            ) {
                Text(
                    modifier = modifier
                        .weight(1f)
                        .background(
                            color = colorResource(resource = SharedR.colors.text_field_background)
                        )
                        .padding(top = 24.dp, bottom = 24.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onEditClick
                        ),
                    text = stringResource(resource = SharedR.strings.add_area_edit_button),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(resource = SharedR.colors.text_title)
                )
            }

        }

        Row {
            AnimatedVisibility(
                modifier = modifier
                    .weight(1f),
                visible = !isEditing
            ) {
                Row {
                    Text(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = colorResource(resource = SharedR.colors.onboarding_background_gradient_start)
                            )
                            .padding(top = 24.dp, bottom = 48.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = onLeaveClick
                            ),
                        text = stringResource(resource = SharedR.strings.add_area_leave_button),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(resource = SharedR.colors.text_light)
                    )
                    Text(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = colorResource(resource = SharedR.colors.background_item)
                            )
                            .padding(top = 24.dp, bottom = 48.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = onDeleteClick
                            ),
                        text = stringResource(resource = SharedR.strings.add_area_delete_button),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                }
            }
        }
    }

    Column {
        Spacer(modifier = modifier.weight(1f))
        AnimatedVisibility(
            modifier = modifier.fillMaxWidth(),
            visible = isEditing
        ) {
            Text(
                modifier = modifier
                    .weight(1f)
                    .background(
                        color = colorResource(resource = SharedR.colors.onboarding_background_gradient_start)
                    )
                    .padding(top = 24.dp, bottom = 48.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onSaveClick
                    ),
                text = stringResource(resource = SharedR.strings.add_area_save_button),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_light)
            )
        }
    }
}