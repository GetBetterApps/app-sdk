package com.velkonost.getbetter.android.features.detail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.shared.features.detail.presentation.DetailViewModel
import com.velkonost.getbetter.shared.features.detail.presentation.models.Decrement
import com.velkonost.getbetter.shared.features.detail.presentation.models.Increment

@Composable
internal fun DetailScreen(
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()

    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Text(text = "count = ${state.count}")

            Button(
                modifier = modifier.padding(top = 15.dp),
                onClick = {
                    viewModel.dispatch(Increment)
                }
            ) {
                Text(text = "increment")
            }

            Button(
                modifier = modifier.padding(top = 15.dp),
                onClick = {
                    viewModel.dispatch(Decrement)
                }
            ) {
                Text(text = "decrement")
            }

            Button(
                modifier = modifier.padding(top = 15.dp),
                onClick = {
                    backPressedDispatcher?.onBackPressed()
                }
            ) {
                Text(text = "back")
            }
        }


    }

}
