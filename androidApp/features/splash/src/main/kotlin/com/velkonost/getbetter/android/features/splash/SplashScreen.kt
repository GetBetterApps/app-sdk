package com.velkonost.getbetter.android.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.features.splash.presentation.SplashViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    @Suppress("unused") viewModel: SplashViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(resource = SharedR.colors.onboarding_background_gradient_start),
                        colorResource(resource = SharedR.colors.onboarding_background_gradient_end)
                    )
                )
            )
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .alpha(0.4f)
                .align(Alignment.Center),
            painter = painterResource(id = SharedR.images.ic_getbetter_light_.drawableResId),
            contentDescription = null
        )
    }
}