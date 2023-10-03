package com.velkonost.getbetter.android.features.diary.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.android.features.diary.notes.components.AddNoteItem
import com.velkonost.getbetter.core.compose.components.Loader

@Composable
fun NotesView(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    createGoalClick: () -> Unit,
    createNoteClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Text(text = "notes")

            AddNoteItem(
                createGoalClick = createGoalClick,
                createNoteClick = createNoteClick
            )
        }
    }
}