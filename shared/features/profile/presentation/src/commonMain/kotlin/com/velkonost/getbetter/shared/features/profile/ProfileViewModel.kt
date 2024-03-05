package com.velkonost.getbetter.shared.features.profile

import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.core.model.subscription.Subscription
import com.velkonost.getbetter.shared.core.model.user.UserInfo
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onFailure
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.auth.api.AuthRepository
import com.velkonost.getbetter.shared.features.profile.api.ProfileRepository
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarSelected
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarSelectedBase64
import com.velkonost.getbetter.shared.features.profile.contracts.ContactUsClick
import com.velkonost.getbetter.shared.features.profile.contracts.LogoutClick
import com.velkonost.getbetter.shared.features.profile.contracts.NavigateToAuth
import com.velkonost.getbetter.shared.features.profile.contracts.NavigateToFeedback
import com.velkonost.getbetter.shared.features.profile.contracts.NavigateToSettings
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileAction
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileEvent
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileNavigation
import com.velkonost.getbetter.shared.features.profile.contracts.ProfileViewState
import com.velkonost.getbetter.shared.features.profile.contracts.SettingsClick
import com.velkonost.getbetter.shared.features.profile.contracts.SignUpClick
import com.velkonost.getbetter.shared.features.profile.contracts.StartTrialClick
import com.velkonost.getbetter.shared.features.profile.contracts.SubscriptionClick
import com.velkonost.getbetter.shared.features.profile.contracts.SubscriptionData
import com.velkonost.getbetter.shared.features.profile.contracts.ThemeChange
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository
import com.velkonost.getbetter.shared.features.subscription.domain.CheckSubscriptionUseCase
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import io.ktor.util.decodeBase64Bytes

class ProfileViewModel
internal constructor(
    private val authRepository: AuthRepository<String>,
    private val userInfoRepository: UserInfoRepository,
    private val profileRepository: ProfileRepository,
    private val subscriptionRepository: SubscriptionRepository,
    private val checkSubscriptionUseCase: CheckSubscriptionUseCase
) : BaseViewModel<ProfileViewState, ProfileAction, ProfileNavigation, ProfileEvent>(
    initialState = ProfileViewState()
) {

//    init {
//        fetchUserInfo()
//    }

    fun onAppear() {
        fetchUserInfo()
        fetchSubscriptionInfo()
    }

    override fun dispatch(action: ProfileAction) = when (action) {
        is LogoutClick -> obtainLogout()
        is SignUpClick -> obtainSignUpClick()
        is SettingsClick -> obtainSettingsClick()
        is ThemeChange -> obtainThemeChange(action.value)
        is AvatarSelected -> obtainAvatarSelected(action.avatarContent)
        is AvatarSelectedBase64 -> obtainAvatarSelected(action.avatarContent.decodeBase64Bytes())
        is ContactUsClick -> emit(NavigateToFeedback)
        is SubscriptionClick -> emit(action)
        is StartTrialClick -> obtainStartTrial()
    }

    // ios fix
    private fun checkLoggingState() {
        launchJob {
            val isLoggedIn = authRepository.isUserLoggedIn()
            if (!isLoggedIn) {
                emit(NavigateToAuth(identifyAnonymous = false))
            }
        }
    }

    private fun fetchUserInfo() {
        launchJob {
            emit(viewState.value.copy(selectedTheme = profileRepository.getTheme()))

            userInfoRepository.fetchInfo() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { userInfo ->
                    userInfo?.toUI()
                }
                onFailure {
                    checkLoggingState()
                }
            }
        }
    }

    private fun fetchSubscriptionInfo() {
        launchJob {
            checkSubscriptionUseCase() collectAndProcess {
                onSuccess {
                    val subscriptionData = SubscriptionData(
                        subscription = it,
                        available = it?.fake == false
                    )

                    emit(viewState.value.copy(subscriptionData = subscriptionData))
                }

                onFailure {
                    val subscriptionData = SubscriptionData(
                        subscription = Subscription.NoSubscription,
                        available = false
                    )

                    emit(viewState.value.copy(subscriptionData = subscriptionData))
                }
            }
        }
    }

    private fun obtainStartTrial() {
        launchJob {
            subscriptionRepository.startTrial() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }

                onSuccess {
                    val subscriptionData = SubscriptionData(
                        subscription = it,
                        available = it?.fake == false
                    )

                    emit(viewState.value.copy(subscriptionData = subscriptionData))
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

    private fun obtainSettingsClick() {
        emit(NavigateToSettings)
    }

    private fun obtainSignUpClick() {
        emit(NavigateToAuth(identifyAnonymous = true))
    }

    private fun obtainLogout() {
        launchJob {
            userInfoRepository.logout() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLogoutLoading = it))
                }
                onSuccess {
                    emit(NavigateToAuth(identifyAnonymous = false))
                }
                onFailure {
                    emit(NavigateToAuth(identifyAnonymous = false))
                }
            }
        }
    }

    private fun obtainThemeChange(value: UIThemeMode) {
        launchJob {
            profileRepository.changeTheme(value)
            emit(viewState.value.copy(selectedTheme = value))
            emit(ProfileEvent.ThemeChanged(value))
        }
    }

    private fun UserInfo?.toUI() {
        this?.let {
            emit(
                viewState.value.copy(
                    userName = it.displayName ?: "",
                    avatarUrl = it.avatarUrl,
                    experienceData = it.experienceData,
                    isUserAnonymous = it.email.isNullOrEmpty()
                )
            )
        }
    }
}