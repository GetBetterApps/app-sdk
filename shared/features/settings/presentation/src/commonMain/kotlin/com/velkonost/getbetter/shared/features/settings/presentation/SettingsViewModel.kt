package com.velkonost.getbetter.shared.features.settings.presentation

import com.velkonost.getbetter.shared.core.model.user.UserInfo
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
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
        else -> {

        }
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

    private fun obtainDeleteAccount() {
        launchJob {
            userInfoRepository.deleteAccount() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    emit(SettingsNavigation.NavigateToAuth)
                }
            }
        }
    }

    private fun obtainNameChanged(value: String) {
        emit(viewState.value.copy(name = value))
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

    private fun UserInfo.toUI() {
        emit(
            viewState.value.copy(
                name = displayName ?: "",
                email = email ?: ""
            )
        )
    }

}