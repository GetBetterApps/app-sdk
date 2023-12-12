package com.velkonost.getbetter.android.features.abilitydetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.notelist.NoteItem
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.core.compose.extensions.fadingEdge
import com.velkonost.getbetter.shared.core.model.note.Note

@Composable
fun AbilityNotesContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    items: List<Note>,
    loadMorePrefetch: Int,
    itemClick: (Note) -> Unit,
    itemLikeClick: (Note) -> Unit,
    onBottomReach: () -> Unit
) {

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .padding(top = 40.dp)
            .fillMaxSize()
            .fadingEdge(),
        state = listState,
        contentPadding = PaddingValues(bottom = 140.dp, top = 20.dp)
    ) {
        items(items, key = { it.id }) { item ->
            NoteItem(
                item = item,
                onClick = itemClick,
                onLikeClick = itemLikeClick
            )
        }

        if (isLoading) {
            item {
                Box(modifier = modifier.fillMaxSize()) {
                    Loader(modifier = modifier.align(Alignment.Center))
                }
            }
        }

    }

    listState.OnBottomReached(
        buffer = loadMorePrefetch,
        isLoading = isLoading
    ) {
        onBottomReach.invoke()
    }

}