package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

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

    AnimatedVisibility(
        visible = isEditing,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column {
            Spacer(modifier = modifier.weight(1f))
            Row {
                CancelSaveButton(onClick = onCancelSaveClick)
                SaveButton(onClick = onSaveClick)
            }
            Spacer(modifier = modifier.height(64.dp))
        }
    }

    AnimatedVisibility(
        visible = !isEditing,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column {
            Spacer(modifier = modifier.weight(1f))
            Row {
                JoinButton(
                    isVisible = isJoinButtonVisible,
                    onClick = onJoinClick
                )

                LeaveButton(
                    isVisible = isLeaveButtonVisible,
                    onClick = onLeaveClick
                )

                EditButton(
                    isVisible = isEditButtonVisible,
                    onClick = onEditClick
                )

                DeleteButton(
                    isVisible = isDeleteButtonVisible,
                    onClick = onDeleteClick
                )
            }
            Spacer(modifier = modifier.height(64.dp))
        }
    }
}

@Composable
internal fun RowScope.EditButton(isVisible: Boolean, onClick: () -> Unit) {
    if (!isVisible) return
    BottomButton(
        icon = painterResource(imageResource = SharedR.images.ic_edit),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.SaveButton(onClick: () -> Unit) {
    BottomButton(
        icon = painterResource(imageResource = SharedR.images.ic_save),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.CancelSaveButton(onClick: () -> Unit) {
    BottomButton(
        icon = painterResource(imageResource = SharedR.images.ic_cancel),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.DeleteButton(isVisible: Boolean, onClick: () -> Unit) {
    if (!isVisible) return
    BottomButton(
        icon = painterResource(imageResource = SharedR.images.ic_trash),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.JoinButton(isVisible: Boolean, onClick: () -> Unit) {
    if (!isVisible) return
    BottomButton(
        icon = painterResource(imageResource = SharedR.images.ic_enter),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.LeaveButton(isVisible: Boolean, onClick: () -> Unit) {
    if (!isVisible) return
    BottomButton(
        icon = painterResource(imageResource = SharedR.images.ic_exit),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.BottomButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Box(modifier = modifier.weight(1f)) {
        Box(
            modifier = modifier
                .shadow(
                    elevation = 8.dp,
                    shape = MaterialTheme.shapes.small,
                )
                .align(Alignment.Center)
                .size(48.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .clipToBounds()
                .clickable {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick.invoke()
                }
        ) {
            Image(
                modifier = modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                painter = icon,
                colorFilter = ColorFilter.tint(
                    color = colorResource(resource = SharedR.colors.icon_active).copy(alpha = 0.5f)
                ),
                contentDescription = null
            )
        }
    }
}