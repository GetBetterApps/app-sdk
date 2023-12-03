package com.velkonost.getbetter.android.features.diary.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
                contentPadding = PaddingValues(bottom = 140.dp)
            ) {

                if (favoriteItems.isNotEmpty()) {
                    item {
                        Text(
                            modifier = modifier
                                .padding(top = 24.dp, start = 20.dp)
                                .fillMaxWidth(0.8f),
                            text = stringResource(resource = SharedR.strings.note_detail_comments_title),
                            color = colorResource(resource = SharedR.colors.text_primary),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }

                    items(favoriteItems, key = { it.id!! }) { item ->
                        TaskItem(
                            item = item,
                        )
                    }
                }

                if (currentItems.isNotEmpty()) {
                    item {
                        Text(
                            modifier = modifier
                                .padding(top = 24.dp, start = 20.dp)
                                .fillMaxWidth(0.8f),
                            text = stringResource(resource = SharedR.strings.note_detail_comments_title),
                            color = colorResource(resource = SharedR.colors.text_primary),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }

                    items(currentItems, key = { it.id!! }) { item ->
                        TaskItem(
                            item = item,
                        )
                    }
                }

                if (completedItems.isNotEmpty()) {
                    item {
                        Text(
                            modifier = modifier
                                .padding(top = 24.dp, start = 20.dp)
                                .fillMaxWidth(0.8f),
                            text = stringResource(resource = SharedR.strings.note_detail_comments_title),
                            color = colorResource(resource = SharedR.colors.text_primary),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }

                    items(completedItems, key = { it.id!! }) { item ->
                        TaskItem(
                            item = item,
                        )
                    }
                }
            }
        }
    }
}