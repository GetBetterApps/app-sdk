package com.velkonost.getbetter.shared.features.profiledetail.presentation

import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.follows.api.FollowsRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailAction
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailEvent
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailNavigation
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository

class ProfileDetailViewModel
internal constructor(
    private val notesRepository: NotesRepository,
    private val followsRepository: FollowsRepository,
    private val userInfoRepository: UserInfoRepository,
) : BaseViewModel<ProfileDetailViewState, ProfileDetailAction, ProfileDetailNavigation, ProfileDetailEvent>(
    initialState = ProfileDetailViewState()
) {

    private val _notesPagingConfig = PagingConfig()

    override fun dispatch(action: ProfileDetailAction) = when (action) {
        else -> {

        }
    }

    private fun fetchUser(userId: String) {
        launchJob {
            userInfoRepository.fetchInfoAboutOtherUser(userId) collectAndProcess {
                isLoading {
                    val profileData = viewState.value.profileData.copy(isLoading = it)
                    emit(viewState.value.copy(profileData = profileData))
                }
                onSuccess { userInfo ->
                    userInfo?.let {
                        val profileData = viewState.value.profileData.copy(
                            userId = it.id,
                            userName = it.displayName ?: "",
                            experienceData = it.experienceData,
                            avatarBytes = it.avatar
                        )
                        emit(viewState.value.copy(profileData = profileData))
                    }
                }
            }
        }
    }

    private fun fetchUserNotes(userId: String) {
        if (_notesPagingConfig.lastPageReached) return

        launchJob {
            notesRepository.fetchOtherUserNotes(
                userId = userId,
                page = _notesPagingConfig.page,
                pageSize = _notesPagingConfig.pageSize,
            ) collectAndProcess {
                isLoading {
                    val notesData = viewState.value.notesData.copy(isLoading = it)
                    emit(viewState.value.copy(notesData = notesData))
                }
                onSuccess { items ->
                    _notesPagingConfig.lastPageReached = items.isNullOrEmpty()
                    _notesPagingConfig.page++

                    items?.let {
                        val uiItems = viewState.value.notesData.items.plus(it)
                        val notesData = viewState.value.notesData.copy(items = uiItems)
                        emit(viewState.value.copy(notesData = notesData))
                    }
                }
            }
        }
    }

}