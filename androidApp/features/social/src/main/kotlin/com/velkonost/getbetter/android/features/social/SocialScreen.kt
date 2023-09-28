package com.velkonost.getbetter.android.features.social

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.Loader

@Composable
fun SocialScreen(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize()) {
        Loader(
            modifier = Modifier.align(Alignment.Center)
        )
    }

}