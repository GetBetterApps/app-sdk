package com.velkonost.getbetter.shared.features.profiledetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.user.ExperienceData
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class ProfileDetailViewState(
    val isLoading: Boolean = false,
    val profileData: ProfileUI = ProfileUI(),
    val notes: List<Note> = emptyList(),
    val followState: FollowState = FollowState.Unfollowed
) : UIContract.State

data class ProfileUI(
    val userName: String = "",
    val avatarBytes: ByteArray? = null,
    val experienceData: ExperienceData? = null,
)

enum class FollowState {
    Followed,
    Unfollowed
}