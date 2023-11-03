package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.BottomActionButton
import com.velkonost.getbetter.shared.resources.SharedR
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
    BottomActionButton(
        icon = painterResource(imageResource = SharedR.images.ic_edit),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.SaveButton(onClick: () -> Unit) {
    BottomActionButton(
        icon = painterResource(imageResource = SharedR.images.ic_save),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.CancelSaveButton(onClick: () -> Unit) {
    BottomActionButton(
        icon = painterResource(imageResource = SharedR.images.ic_cancel),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.DeleteButton(isVisible: Boolean, onClick: () -> Unit) {
    if (!isVisible) return
    BottomActionButton(
        icon = painterResource(imageResource = SharedR.images.ic_trash),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.JoinButton(isVisible: Boolean, onClick: () -> Unit) {
    if (!isVisible) return
    BottomActionButton(
        icon = painterResource(imageResource = SharedR.images.ic_enter),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.LeaveButton(isVisible: Boolean, onClick: () -> Unit) {
    if (!isVisible) return
    BottomActionButton(
        icon = painterResource(imageResource = SharedR.images.ic_exit),
        onClick = onClick
    )
}
