package com.velkonost.getbetter.android.features.splash

import android.content.Context
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.features.splash.presentation.SplashViewModel
import com.velkonost.getbetter.shared.features.splash.presentation.contract.SplashAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    @Suppress("unused") viewModel: SplashViewModel
) {

    val context = LocalContext.current
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    state.selectedTheme?.let {
        LaunchedEffect(state.selectedTheme) {
            val mode = when (state.selectedTheme) {
                UIThemeMode.LightTheme -> AppCompatDelegate.MODE_NIGHT_NO
                UIThemeMode.DarkTheme -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
            if (AppCompatDelegate.getDefaultNightMode() != mode) {
                AppCompatDelegate.setDefaultNightMode(mode)
            }
        }

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

    LaunchedEffect(Unit) {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryCodeValue = tm.networkCountryIso
        viewModel.dispatch(SplashAction.AllowSubscription(countryCodeValue.isNotEmpty()))
    }
}