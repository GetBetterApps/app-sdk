package com.velkonost.getbetter.android.features.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.settings.components.SettingsHeader
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.SingleLineTextField
import com.velkonost.getbetter.core.compose.components.WhiteButton
import com.velkonost.getbetter.shared.features.settings.presentation.SettingsViewModel
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsAction

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Column {
                SettingsHeader { viewModel.dispatch(SettingsAction.NavigateBack) }

                SingleLineTextField(
                    value = state.email,
                    placeholderText = "email",
                    onValueChanged = {},
                    isEnabled = false
                )

                SingleLineTextField(
                    value = state.name,
                    placeholderText = "name",
                    onValueChanged = {
                        viewModel.dispatch(SettingsAction.NameChanged(it))
                    }
                )

                Spacer(modifier.weight(1f))

                AppButton(
                    labelText = "change password",
                    isLoading = state.isLoading,
                    onClick = {
                        viewModel.dispatch(SettingsAction.ChangePasswordClick)
                    }
                )

                WhiteButton(
                    labelText = "delete",
                    isLoading = state.isLoading,
                    onClick = {

                    }
                )
            }
        }
    }
}