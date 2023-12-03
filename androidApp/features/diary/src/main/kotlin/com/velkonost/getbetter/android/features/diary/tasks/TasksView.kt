package com.velkonost.getbetter.android.features.diary.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.shared.core.model.task.Task

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
            Text(text = "tasks")
        }
    }
}