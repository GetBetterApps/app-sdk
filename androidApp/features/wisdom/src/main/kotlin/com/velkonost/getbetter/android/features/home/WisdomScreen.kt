package com.velkonost.getbetter.android.features.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.home.components.WisdomItem
import com.velkonost.getbetter.shared.features.presentation.WisdomViewModel
import com.velkonost.getbetter.shared.features.presentation.contracts.WisdomItemClick
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun WisdomScreen(
    modifier: Modifier = Modifier,
    viewModel: WisdomViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 140.dp, top = 28.dp)
    ) {

        items(state.items) { item ->
            WisdomItem(
                title = item.title.toString(context = context),
                description = item.description.toString(context = context),
                backgroundImage = painterResource(imageResource = item.backgroundImage)
            ) {
                viewModel.dispatch(WisdomItemClick(item))
            }
        }

    }
}