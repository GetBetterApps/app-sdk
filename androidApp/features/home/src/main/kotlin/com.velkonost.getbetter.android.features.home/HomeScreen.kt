package com.velkonost.getbetter.android.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.velkonost.getbetter.shared.features.home.presentation.HomeViewModel
import com.velkonost.getbetter.shared.features.home.presentation.contracts.NavigateToDetails

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        Column {

            Text(text = "123", color = Color.Red, fontSize = 30.sp)

            Button(onClick = {
                viewModel.dispatch(NavigateToDetails)
            }) {
                Text(text = "Click me mthfcr", color = Color.Green, fontSize = 20.sp)
            }
        }

    }

}
