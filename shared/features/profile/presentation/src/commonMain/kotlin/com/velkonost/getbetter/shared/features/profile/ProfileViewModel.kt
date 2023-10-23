package com.velkonost.getbetter.shared.features.profile

import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarSelected
import com.velkonost.getbetter.shared.features.profile.contracts.LogoutClick
import com.velkonost.getbetter.shared.features.profile.contracts.NavigateToAuth
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileAction
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileNavigation
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository

class ProfileViewModel
internal constructor(
    private val userInfoRepository: UserInfoRepository
) : BaseViewModel<ProfileViewState, ProfileAction, ProfileNavigation, Nothing>(
    initialState = ProfileViewState()
) {

    init {
        fetchUserInfo()
    }

    override fun dispatch(action: ProfileAction) = when (action) {
        is LogoutClick -> obtainLogout()
        is AvatarSelected -> obtainAvatarSelected(action.avatarContent)
    }

    private fun fetchUserInfo() {
        launchJob {
            userInfoRepository.fetchInfo()
                .collect { result ->
                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLoading = it))
                        }
                        onSuccess { userInfo ->
                            userInfo?.let {
                                emit(
                                    viewState.value.copy(
                                        userName = it.locale,
                                        avatarBytes = it.avatar
                                    )
                                )
                            }
                        }
                        onFailureWithMsg { _, message ->
                            message?.let { emit(it) }
                        }
                    }
                }
        }
    }

    private fun obtainAvatarSelected(fileContent: ByteArray) {
        launchJob {
            userInfoRepository.updateAvatarUrl(fileContent)
                .collect { result ->
                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLoading = it))
                        }

                        onSuccess { userInfo ->
                            userInfo?.let {
                                emit(
                                    viewState.value.copy(
                                        userName = it.locale,
                                        avatarBytes = it.avatar
                                    )
                                )
                            }
                        }

                        onFailureWithMsg { _, message ->
                            message?.let { emit(it) }
                        }
                    }

                }
        }
    }

    private fun obtainLogout() {
        launchJob {
            userInfoRepository.logout()
                .collect { result ->

                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLogoutLoading = it))
                        }
                        onSuccess {
                            emit(NavigateToAuth)
                        }
                        onFailureWithMsg { _, message ->
                            message?.let { emit(it) }
                        }
                    }
                }
        }
    }

}