package com.velkonost.getbetter.android.features.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.auth.components.AuthAnonymousButton
import com.velkonost.getbetter.android.features.auth.components.AuthFooterText
import com.velkonost.getbetter.android.features.auth.components.AuthTextField
import com.velkonost.getbetter.android.features.auth.components.InputType
import com.velkonost.getbetter.android.features.auth.components.Logo
import com.velkonost.getbetter.android.features.auth.components.SwitchRegisteringText
import com.velkonost.getbetter.core.compose.components.WhiteButton
import com.velkonost.getbetter.shared.features.auth.presentation.AuthViewModel
import com.velkonost.getbetter.shared.features.auth.presentation.models.AuthAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val localFocusManager = LocalFocusManager.current

    val haptic = LocalHapticFeedback.current


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
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {

        Column(
            modifier = modifier.padding(top = 50.dp)
        ) {

            AuthAnonymousButton(modifier = modifier, isEnabled = !state.isLoading, haptic) {
                viewModel.dispatch(AuthAction.AnonymousLoginClick)
            }

            Logo(modifier = modifier)

            Spacer(modifier = modifier.height(32.dp))

            EmailTextField(modifier = modifier, value = state.email) {
                viewModel.dispatch(AuthAction.EmailChanged(it))
            }

            PasswordTextField(modifier = modifier, value = state.password) {
                viewModel.dispatch(AuthAction.PasswordChanged(it))
            }

            SwitchRegisteringText(modifier = modifier, targetState = state.isRegistering, haptic) {
                viewModel.dispatch(AuthAction.SwitchAuthClick)
            }

            Spacer(modifier = modifier.weight(1f))

            WhiteButton(
                modifier = modifier,
                labelText = stringResource(
                    resource =
                    if (state.isRegistering) SharedR.strings.auth_signup_button
                    else SharedR.strings.auth_login_button
                ),
                isLoading = state.isLoading, haptic
            ) {
                viewModel.dispatch(AuthAction.LoginClick)
            }

            Spacer(modifier = modifier.height(32.dp))

            AuthFooterText(modifier = modifier)
        }
    }
}

@Composable
fun EmailTextField(
    modifier: Modifier,
    value: String,
    onValueChanged: (String) -> Unit
) {
    AuthTextField(
        modifier = modifier,
        placeholderText = stringResource(resource = SharedR.strings.auth_email_label),
        value = value,
        inputType = InputType.Email,
        onValueChanged = {
            onValueChanged.invoke(it)
        }
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier,
    value: String,
    onValueChanged: (String) -> Unit
) {
    AuthTextField(
        modifier = modifier,
        placeholderText = stringResource(resource = SharedR.strings.auth_password_label),
        value = value,
        inputType = InputType.Password,
        onValueChanged = {
            onValueChanged.invoke(it)
        }
    )
}
