package com.velkonost.getbetter.shared.features.profile

import com.velkonost.getbetter.shared.core.model.profile.UIMode
import com.velkonost.getbetter.shared.core.model.user.UserInfo
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarSelected
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarSelectedBase64
import com.velkonost.getbetter.shared.features.profile.contracts.LogoutClick
import com.velkonost.getbetter.shared.features.profile.contracts.NavigateToAuth
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileAction
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileNavigation
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileViewState
import com.velkonost.getbetter.shared.features.profile.contracts.ThemeChange
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import io.ktor.util.decodeBase64Bytes

class ProfileViewModel
internal constructor(
    private val userInfoRepository: UserInfoRepository,
    private val profi
) : BaseViewModel<ProfileViewState, ProfileAction, ProfileNavigation, Nothing>(
    initialState = ProfileViewState()
) {

    init {
        fetchUserInfo()
    }

    fun onAppear() {
        fetchUserInfo()
    }


    override fun dispatch(action: ProfileAction) = when (action) {
        is LogoutClick -> obtainLogout()
        is ThemeChange ->
        is AvatarSelected -> obtainAvatarSelected(action.avatarContent)
        is AvatarSelectedBase64 -> obtainAvatarSelected(action.avatarContent.decodeBase64Bytes())
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

    private fun obtainAvatarSelected(fileContent: ByteArray) {
        launchJob {
            userInfoRepository.updateAvatarUrl(fileContent) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { userInfo ->
                    userInfo?.toUI()
                }
            }
        }
    }

    private fun obtainLogout() {
        launchJob {
            userInfoRepository.logout() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLogoutLoading = it))
                }
                onSuccess {
                    emit(NavigateToAuth)
                }
            }
        }
    }

    private fun obtainThemeChange(value: UIMode) {

    }

    private fun UserInfo?.toUI() {
        this?.let {
            emit(
                viewState.value.copy(
                    userName = it.displayName ?: "",
                    avatarUrl = it.avatarUrl,
                    experienceData = it.experienceData
                )
            )
        }
    }
}