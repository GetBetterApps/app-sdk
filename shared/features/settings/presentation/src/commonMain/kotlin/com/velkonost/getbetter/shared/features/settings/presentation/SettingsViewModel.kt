package com.velkonost.getbetter.shared.features.settings.presentation

import com.velkonost.getbetter.shared.core.model.user.UserInfo
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onFailure
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.settings.presentation.contract.ChangePasswordAction
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsAction
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsEvent
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsNavigation
import com.velkonost.getbetter.shared.features.settings.presentation.contract.SettingsViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository

class SettingsViewModel internal constructor(
    private val userInfoRepository: UserInfoRepository,
) : BaseViewModel<SettingsViewState, SettingsAction, SettingsNavigation, SettingsEvent>(
    initialState = SettingsViewState()
) {

    init {
        fetchUserInfo()
    }

    override fun dispatch(action: SettingsAction) = when (action) {
        is SettingsAction.NavigateBack -> emit(action)
        is SettingsAction.NameChanged -> obtainNameChanged(action.value)
        is SettingsAction.SaveNameClick -> obtainSaveName()
        is SettingsAction.DeleteAccountConfirm -> obtainDeleteAccount()
        is ChangePasswordAction.OldPasswordChanged -> obtainOldPasswordChanged(action.value)
        is ChangePasswordAction.NewPasswordChanged -> obtainNewPasswordChanged(action.value)
        is ChangePasswordAction.RepeatedNewPasswordChanged -> obtainRepeatedNewPasswordChanged(
            action.value
        )

        is ChangePasswordAction.ChangeClick -> obtainChangePassword()
        SettingsAction.ChangePasswordClick -> obtainStartChangePassword()
    }

    private fun fetchUserInfo() {
        launchJob {
            userInfoRepository.fetchInfo() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { userInfo ->
                    userInfo?.toUI()
                }
            }
        }
    }

    private fun obtainStartChangePassword() {
        val changePasswordState = viewState.value.changePasswordState.copy(
            oldPassword = "",
            newPassword = "",
            repeatedNewPassword = ""
        )
        emit(viewState.value.copy(changePasswordState = changePasswordState))
    }

    private fun obtainDeleteAccount() {
        launchJob {
            userInfoRepository.deleteAccount() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    emit(SettingsNavigation.NavigateToAuth)
                }
                onFailure {
                    emit(SettingsNavigation.NavigateToAuth)
                }
            }
        }
    }

    private fun obtainNameChanged(value: String) {
        emit(viewState.value.copy(name = value))
    }

    private fun obtainOldPasswordChanged(value: String) {
        val changePasswordState = viewState.value.changePasswordState.copy(oldPassword = value)
        emit(viewState.value.copy(changePasswordState = changePasswordState))
    }

    private fun obtainNewPasswordChanged(value: String) {
        val changePasswordState = viewState.value.changePasswordState.copy(newPassword = value)
        emit(viewState.value.copy(changePasswordState = changePasswordState))
    }

    private fun obtainRepeatedNewPasswordChanged(value: String) {
        val changePasswordState = viewState.value.changePasswordState.copy(
            repeatedNewPassword = value
        )
        emit(viewState.value.copy(changePasswordState = changePasswordState))
    }

    private fun obtainSaveName() {
        launchJob {
            userInfoRepository.updateName(
                newName = viewState.value.name
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { userInfo ->
                    userInfo?.toUI()
                }
            }
        }
    }

    private fun obtainChangePassword() {
        launchJob {
            val changePasswordState = viewState.value.changePasswordState
            userInfoRepository.changePassword(
                oldPassword = changePasswordState.oldPassword,
                newPassword = changePasswordState.newPassword,
                newRepeatedPassword = changePasswordState.repeatedNewPassword
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    emit(SettingsEvent.PasswordChanged)
                }
            }
        }
    }

    private fun UserInfo.toUI() {
        emit(
            viewState.value.copy(
                name = displayName ?: "",
                email = email ?: ""
            )
        )
    }

}