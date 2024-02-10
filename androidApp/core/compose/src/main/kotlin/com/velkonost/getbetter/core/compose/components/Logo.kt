package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.velkonost.getbetter.shared.resources.SharedR

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .alpha(0.4f),
        painter = painterResource(id = SharedR.images.ic_getbetter_light_.drawableResId),
        contentDescription = null
    )
}