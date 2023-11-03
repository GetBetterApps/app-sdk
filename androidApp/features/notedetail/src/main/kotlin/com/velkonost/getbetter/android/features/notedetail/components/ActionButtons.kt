package com.velkonost.getbetter.android.features.notedetail.components

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
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ActionButtons(
    modifier: Modifier = Modifier,
    noteState: NoteState,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelSaveClick: () -> Unit
) {

    AnimatedVisibility(
        visible = noteState == NoteState.Editing,
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
        visible = noteState == NoteState.View,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column {
            Spacer(modifier = modifier.weight(1f))
            Row {
                DeleteButton(
                    onClick = onDeleteClick
                )

                EditButton(
                    onClick = onEditClick
                )
            }
        }
    }
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
internal fun RowScope.DeleteButton(onClick: () -> Unit) {
    BottomActionButton(
        icon = painterResource(imageResource = SharedR.images.ic_trash),
        onClick = onClick
    )
}

@Composable
internal fun RowScope.EditButton(onClick: () -> Unit) {
    BottomActionButton(
        icon = painterResource(imageResource = SharedR.images.ic_edit),
        onClick = onClick
    )
}
