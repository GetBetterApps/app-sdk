package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun PlaceholderView(
    modifier: Modifier = Modifier,
    text: String
) {

    val availableAnimations =
        listOf(
            SharedR.files.anim_placeholder_1,
            SharedR.files.anim_placeholder_2,
            SharedR.files.anim_placeholder_3,
            SharedR.files.anim_placeholder_4,
            SharedR.files.anim_placeholder_5,
            SharedR.files.anim_placeholder_6,
        )

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(availableAnimations.random().rawResId)
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier.weight(1f))

        LottieAnimation(
            modifier = modifier.size(128.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        Text(
            modifier = modifier.padding(top = 12.dp, start = 32.dp, end = 32.dp),
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(resource = SharedR.colors.text_title),
            textAlign = TextAlign.Center
        )

        Spacer(modifier.weight(1f))
    }
}
