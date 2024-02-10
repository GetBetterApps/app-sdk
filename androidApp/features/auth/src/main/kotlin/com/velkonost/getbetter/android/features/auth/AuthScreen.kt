package com.velkonost.getbetter.android.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.auth.components.AuthAnonymousButton
import com.velkonost.getbetter.android.features.auth.components.AuthFooterText
import com.velkonost.getbetter.android.features.auth.components.AuthTextField
import com.velkonost.getbetter.android.features.auth.components.InputType
import com.velkonost.getbetter.android.features.auth.components.SwitchRegisteringText
import com.velkonost.getbetter.core.compose.components.Logo
import com.velkonost.getbetter.core.compose.components.WhiteButton
import com.velkonost.getbetter.core.compose.components.webview.AppWebView
import com.velkonost.getbetter.shared.features.auth.presentation.AuthViewModel
import com.velkonost.getbetter.shared.features.auth.presentation.contracts.AuthAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    forceHideBottomBar: MutableState<Boolean> = mutableStateOf(false)
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val localFocusManager = LocalFocusManager.current
    val haptic = LocalHapticFeedback.current

    val webViewSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
    val scope = rememberCoroutineScope()
    val webViewLink = remember { mutableStateOf<String?>(null) }

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

            Logo()

            Spacer(modifier = modifier.height(32.dp))

            EmailTextField(modifier = modifier, value = state.email) {
                viewModel.dispatch(AuthAction.EmailChanged(it))
            }

            PasswordTextField(modifier = modifier, value = state.password) {
                viewModel.dispatch(AuthAction.PasswordChanged(it))
            }

            if (!state.forceSignUp) {
                SwitchRegisteringText(
                    modifier = modifier,
                    targetState = state.isRegistering,
                    haptic
                ) { viewModel.dispatch(AuthAction.SwitchAuthClick) }
            }

            Spacer(modifier = modifier.weight(1f))

            WhiteButton(
                modifier = modifier,
                labelText = stringResource(
                    resource =
                    if (state.isRegistering) SharedR.strings.auth_signup_button
                    else SharedR.strings.auth_login_button
                ),
                isLoading = state.isLoading
            ) {
                viewModel.dispatch(AuthAction.LoginClick)
            }

            Spacer(modifier = modifier.height(32.dp))

            AuthFooterText(
                modifier = modifier,
                onPrivacyClick = {
                    scope.launch {
                        webViewLink.value = state.privacyLink
                        webViewSheetState.show()
                    }
                },
                onTermsClick = {
                    scope.launch {
                        webViewLink.value = state.termsLink
                        webViewSheetState.show()
                    }
                }
            )
        }
    }

    AppWebView(
        link = webViewLink.value,
        sheetState = webViewSheetState
    )

    LaunchedEffect(Unit) {
        combine(
            snapshotFlow { webViewSheetState.currentValue }
        ) { sheetState ->
            val hideBottomBar = sheetState.first() != ModalBottomSheetValue.Hidden
            hideBottomBar
        }.collect {
            forceHideBottomBar.value = it
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
