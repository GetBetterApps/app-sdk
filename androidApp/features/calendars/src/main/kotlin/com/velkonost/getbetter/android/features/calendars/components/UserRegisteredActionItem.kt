package com.velkonost.getbetter.android.features.calendars.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun UserRegisteredActionItem(
    modifier: Modifier = Modifier
) {

    PrimaryBox(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp),
        padding = 0,
        topPadding = 0
    ) {

        Box(
            modifier = modifier
                .height(180.dp)
                .align(Alignment.Center)
        ) {
            Image(
                modifier = modifier
                    .align(Alignment.Center)
                    .size(128.dp)
                    .clip(MaterialTheme.shapes.small),
                painter = painterResource(imageResource = SharedR.images.logo),
                contentDescription = null
            )

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(SharedR.files.confetti.rawResId)
            )

            LottieAnimation(
                modifier = modifier.fillMaxSize(),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}