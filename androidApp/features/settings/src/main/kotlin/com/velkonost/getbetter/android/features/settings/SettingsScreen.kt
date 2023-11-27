package com.velkonost.getbetter.android.features.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.settings.components.ChangePasswordBottomSheet
import com.velkonost.getbetter.android.features.settings.components.NameTextField
import com.velkonost.getbetter.android.features.settings.components.SettingsHeader
import com.velkonost.getbetter.core.compose.components.AppAlertDialog
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.SingleLineTextField
import com.velkonost.getbetter.core.compose.components.WhiteButton
import com.velkonost.getbetter.shared.features.settings.presentation.SettingsViewModel
import com.velkonost.getbetter.shared.features.settings.presentation.contract.ChangePasswordAction
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsAction
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsEvent
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val confirmDeleteAccountDialog = remember { mutableStateOf(false) }
    val changePasswordSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsHeader { viewModel.dispatch(SettingsAction.NavigateBack) }

                SingleLineTextField(
                    value = state.email,
                    placeholderText = stringResource(resource = SharedR.strings.auth_email_label),
                    onValueChanged = {},
                    isEnabled = false,
                    paddingValues = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp)
                )

                NameTextField(
                    value = state.name,
                    placeholderText = "name",
                    onValueChanged = {
                        viewModel.dispatch(SettingsAction.NameChanged(it))
                    },
                    onSaveClick = {
                        viewModel.dispatch(SettingsAction.SaveNameClick)
                    }
                )

                Spacer(modifier.weight(1f))

                AppButton(
                    labelText = "change password",
                    isLoading = state.isLoading,
                    onClick = {
                        viewModel.dispatch(SettingsAction.ChangePasswordClick)
                        scope.launch {
                            changePasswordSheetState.show()
                        }
                    }
                )

                WhiteButton(
                    modifier = modifier.padding(top = 16.dp),
                    labelText = "delete",
                    height = 42,
                    isLoading = state.isLoading,
                    onClick = {
                        confirmDeleteAccountDialog.value = true
                    }
                )

                Spacer(modifier.height(64.dp))
            }
        }
    }

    if (confirmDeleteAccountDialog.value) {
        AppAlertDialog(
            title = stringResource(resource = SharedR.strings.settings_delete_account_title),
            text = stringResource(resource = SharedR.strings.settings_delete_account_text),
            confirmTitle = stringResource(resource = SharedR.strings.confirm),
            cancelTitle = stringResource(resource = SharedR.strings.cancel),
            onDismiss = { confirmDeleteAccountDialog.value = false },
            onConfirmClick = {
                viewModel.dispatch(SettingsAction.DeleteAccountConfirm)
                confirmDeleteAccountDialog.value = false
            }
        )
    }

    ChangePasswordBottomSheet(
        changePasswordState = state.changePasswordState,
        modalSheetState = changePasswordSheetState,
        onOldPasswordChanged = {
            viewModel.dispatch(ChangePasswordAction.OldPasswordChanged(it))
        },
        onNewPasswordChanged = {
            viewModel.dispatch(ChangePasswordAction.NewPasswordChanged(it))
        },
        onRepeatedNewPasswordChanged = {
            viewModel.dispatch(ChangePasswordAction.RepeatedNewPasswordChanged(it))
        },
        onChangedClick = {
            viewModel.dispatch(ChangePasswordAction.ChangeClick)
        }
    )

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest {
            when (it) {
                is SettingsEvent.PasswordChanged -> {
                    scope.launch {
                        changePasswordSheetState.hide()
                    }
                }
            }
        }
    }
}