package com.velkonost.getbetter.android.features.abilities

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.shared.features.abilities.presentation.AbilitiesViewModel

@Composable
fun AbilitiesScreen(
    modifier: Modifier = Modifier,
    viewModel: AbilitiesViewModel
) {

    val listState = rememberLazyListState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

}