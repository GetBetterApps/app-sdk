package com.velkonost.getbetter.shared.features.profiledetail.presentation

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
    override fun dispatch(action: ProfileDetailAction) = when (action) {
        else -> {

        }
    }

    private fun fetchUser(userId: String) {
        launchJob {
            userInfoRepository.fetchInfoAboutOtherUser(userId) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {

                }
            }
        }
    }

    private fun fetchUserNotes() {
        launchJob {

        }
    }

}