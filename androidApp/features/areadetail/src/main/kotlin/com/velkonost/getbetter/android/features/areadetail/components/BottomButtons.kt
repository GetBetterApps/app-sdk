package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BottomButtons(
    modifier: Modifier = Modifier,
    isJoinButtonVisible: Boolean,
    isEditButtonVisible: Boolean,
    isDeleteButtonVisible: Boolean,
    isLeaveButtonVisible: Boolean,
    isEditing: Boolean,
    onJoinClick: () -> Unit,
    onEditClick: () -> Unit,
    onLeaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelSaveClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }

    Row {
        AnimatedVisibility(
            modifier = modifier.weight(1f),
            visible = !isEditing && isEditButtonVisible
        ) {
            Box(
                modifier = modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = MaterialTheme.shapes.small,
                    )
                    .size(48.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.background_icon),
                        shape = MaterialTheme.shapes.small
                    )
                    .clickable {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
//                        onClick.invoke()
                    }
            ) {
                Image(
                    modifier = modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(imageResource = SharedR.images.ic_settings),
                    colorFilter = ColorFilter.tint(
                        color = colorResource(resource = SharedR.colors.icon_active).copy(alpha = 0.5f)
                    ),
                    contentDescription = null
                )
            }
        }
    }

    Column {
        Spacer(modifier = modifier.weight(1f))

        Row {
            AnimatedVisibility(
                modifier = modifier
                    .weight(1f),
                visible = !isEditing && isEditButtonVisible
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
                            onClick = {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                onEditClick()
                            }
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
                    if (isLeaveButtonVisible) {
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
                                    onClick = {
                                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                        onLeaveClick()
                                    }
                                ),
                            text = stringResource(resource = SharedR.strings.add_area_leave_button),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                            color = colorResource(resource = SharedR.colors.text_light)
                        )
                    }

                    if (isDeleteButtonVisible) {
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
                                    onClick = {
                                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                        onDeleteClick()
                                    }
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
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            onSaveClick()
                        }
                    ),
                text = stringResource(resource = SharedR.strings.add_area_save_button),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_light)
            )
        }
    }
}