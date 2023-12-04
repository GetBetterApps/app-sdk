package com.velkonost.getbetter.android.features.diary.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.tasks.components.TaskItem
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.features.calendars.model.TasksUISection
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TasksView(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    favoriteItems: List<TaskUI>,
    currentItems: List<TaskUI>,
    completedItems: List<TaskUI>,
    onTaskClick: (TaskUI) -> Unit,
    onTaskListUpdateClick: () -> Unit,
    onTaskFavoriteClick: (TaskUI) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading && currentItems.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .fadingEdge(),
                contentPadding = PaddingValues(bottom = 140.dp),
            ) {
                tasksSection(
                    section = TasksUISection.Favorite,
                    items = favoriteItems,
                    onItemClick = onTaskClick,
                    onFavoriteClick = onTaskFavoriteClick
                )

                tasksSection(
                    section = TasksUISection.Current,
                    items = currentItems,
                    onItemClick = onTaskClick,
                    onFavoriteClick = onTaskFavoriteClick,
                    onUpdateClick = onTaskListUpdateClick
                )

                tasksSection(
                    section = TasksUISection.Completed,
                    items = completedItems,
                    onItemClick = onTaskClick,
                    onFavoriteClick = onTaskFavoriteClick
                )
            }
        }
    }
}

fun LazyListScope.tasksSection(
    modifier: Modifier = Modifier,
    section: TasksUISection,
    items: List<TaskUI>,
    onItemClick: (TaskUI) -> Unit,
    onFavoriteClick: (TaskUI) -> Unit,
    onUpdateClick: (() -> Unit)? = null
) {
    if (items.isNotEmpty()) {
        item {
            val title = when (section) {
                TasksUISection.Favorite -> stringResource(resource = SharedR.strings.tasks_favorite_title)
                TasksUISection.Current -> stringResource(resource = SharedR.strings.tasks_current_list_title)
                TasksUISection.Completed -> stringResource(resource = SharedR.strings.tasks_completed_title)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier
                        .padding(top = 24.dp, start = 20.dp)
                        .fillMaxWidth(0.8f),
                    text = title,
                    color = colorResource(resource = SharedR.colors.text_primary),
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier.weight(1f))

                if (onUpdateClick != null) {
                    Text(
                        modifier = modifier
                            .padding(end = 20.dp)
                            .shadow(
                                elevation = 8.dp,
                                shape = MaterialTheme.shapes.small,
                            )
                            .background(
                                color = colorResource(resource = SharedR.colors.button_gradient_start),
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(
                                start = 6.dp,
                                end = 6.dp,
                                top = 4.dp,
                                bottom = 4.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onUpdateClick
                            ),
                        text = stringResource(resource = SharedR.strings.tasks_update_list_title).uppercase(),
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(resource = SharedR.colors.text_light)
                    )
                }
            }
        }

        items(items, key = { it.id!! }) { item ->
            TaskItem(
                item = item,
                onClick = {
                    onItemClick(item)
                },
                onFavoriteClick = {
                    onFavoriteClick(item)
                }
            )
        }
    }
}