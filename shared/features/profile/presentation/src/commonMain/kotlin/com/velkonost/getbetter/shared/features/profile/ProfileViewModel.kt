package com.velkonost.getbetter.shared.features.profile

import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onFailure
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarUploaded
import com.velkonost.getbetter.shared.features.profile.contracts.LogoutClick
import com.velkonost.getbetter.shared.features.profile.contracts.NavigateToAuth
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileAction
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileNavigation
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import dev.gitlive.firebase.auth.FirebaseUser

class ProfileViewModel
internal constructor(
    private val authRepository: AuthRepository<FirebaseUser>,
    private val userInfoRepository: UserInfoRepository
) : BaseViewModel<ProfileViewState, ProfileAction, ProfileNavigation, Nothing>(
    initialState = ProfileViewState()
) {

    init {
        fetchUserInfo()
    }

    override fun dispatch(action: ProfileAction) = when (action) {
        is LogoutClick -> obtainLogout()
        is AvatarUploaded -> obtainAvatarUploaded(action.fileUrl)

        else -> {}
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
                                        avatarUrl = it.avatarUrl
                                    )
                                )
                            }
                        }
                        onFailure {

                        }
                    }
                }
        }
    }

    private fun obtainAvatarUploaded(fileUrl: String) {
        launchJob {
            userInfoRepository.updateAvatarUrl(fileUrl)
                .collect { result ->
                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLoading = true))
                        }
                        onFailure {

                        }
                    }
                }
        }
    }

    private fun obtainLogout() {
        launchJob {
            authRepository.logout()
                .collect { result ->

                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLogoutLoading = it))
                        }
                        onSuccess {
                            emit(NavigateToAuth)
                        }
                        onFailure {

                        }
                    }
                }
        }
    }

}