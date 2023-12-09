package com.velkonost.getbetter.android.features.calendars.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.comments.Comment
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.SubNote
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.ActionUIItem
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun ActionItem(
    modifier: Modifier = Modifier,
    item: ActionUIItem<*, *>,
    onAreaClick: (Int) -> Unit,
    onNoteClick: (Note) -> Unit,
    onTaskClick: (TaskUI) -> Unit,
    onUserClick: () -> Unit
) {
    Column {
        if (item.description != null) {
            Text(
                modifier = modifier
                    .padding(start = 32.dp)
                    .padding(top = 12.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp
                        )
                    )
                    .background(
                        color = colorResource(resource = SharedR.colors.button_gradient_start),
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp
                        )
                    )
                    .padding(vertical = 4.dp, horizontal = 20.dp),
                text = item.description!!.toString(LocalContext.current),
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(resource = SharedR.colors.text_light)
            )
        }

        when {
            item.data is Long -> {
                UserRegisteredActionItem()
            }

            item.data is TaskUI -> {
                TaskActionItem(
                    item = item.data as TaskUI,
                    onClick = {
                        onTaskClick(item.data as TaskUI)
                    }
                )
            }

            item.data is UserInfoShort -> {
                UserActionItem(
                    isLoading = false,
                    item = item.data as UserInfoShort,
                    onClick = onUserClick
                )
            }

            item.relatedData is Note -> {
                NoteActionItem(
                    item = item.relatedData as Note,
                    comment = if (item.data is Comment) item.data as Comment else null,
                    subGoalText = if (item.data is SubNote) (item.data as SubNote).text else null,
                    onClick = onNoteClick,
                    onLikeClick = {

                    }
                )
            }

            item.data is Area -> {
                AreaActionItem(
                    item = item.data as Area,
                    onClick = onAreaClick,
                    onLikeClick = {

                    }
                )
            }

            item.data is Note -> {
                NoteActionItem(
                    item = item.data as Note,
                    onClick = onNoteClick,
                    onLikeClick = {

                    }
                )
            }

            item.relatedData is Area -> {
                AreaActionItem(
                    item = item.relatedData as Area,
                    onClick = onAreaClick,
                    onLikeClick = {

                    }
                )
            }
        }
    }
}