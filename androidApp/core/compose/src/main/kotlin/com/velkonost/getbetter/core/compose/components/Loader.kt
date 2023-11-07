package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.velkonost.getbetter.shared.resources.SharedR

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    size: Int = 128,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            if (isSystemInDarkTheme()) SharedR.files.loader_light.rawResId
            else SharedR.files.loader_dark.rawResId
        )
    )

    LottieAnimation(
        modifier = modifier.size(size.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}