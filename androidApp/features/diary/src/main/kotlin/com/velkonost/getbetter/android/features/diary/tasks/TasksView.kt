package com.velkonost.getbetter.android.features.diary.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.tasks.components.TaskItem
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.core.model.task.Task
import com.velkonost.getbetter.shared.features.calendars.model.TasksUISection
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TasksView(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    favoriteItems: List<Task>,
    currentItems: List<Task>,
    completedItems: List<Task>
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
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
                    items = favoriteItems
                )

                tasksSection(
                    section = TasksUISection.Current,
                    items = currentItems
                )

                tasksSection(
                    section = TasksUISection.Completed,
                    items = completedItems
                )
            }
        }
    }
}

fun LazyListScope.tasksSection(
    modifier: Modifier = Modifier,
    section: TasksUISection,
    items: List<Task>
) {
    if (items.isNotEmpty()) {
        item {
            val title = when (section) {
                TasksUISection.Favorite -> stringResource(resource = SharedR.strings.tasks_favorite_title)
                TasksUISection.Current -> stringResource(resource = SharedR.strings.tasks_current_list_title)
                TasksUISection.Completed -> stringResource(resource = SharedR.strings.tasks_completed_title)
            }

            Text(
                modifier = modifier
                    .padding(top = 24.dp, start = 20.dp)
                    .fillMaxWidth(0.8f),
                text = title,
                color = colorResource(resource = SharedR.colors.text_primary),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        items(items, key = { it.id!! }) { item ->
            TaskItem(
                item = item,
            )
        }
    }
}