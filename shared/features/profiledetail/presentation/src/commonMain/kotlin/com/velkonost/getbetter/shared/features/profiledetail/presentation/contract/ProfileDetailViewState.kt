package com.velkonost.getbetter.shared.features.profiledetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.user.ExperienceData
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class ProfileDetailViewState(
    val profileData: ProfileUI = ProfileUI(),
    val notesData: NotesUI = NotesUI(),
    val followState: FollowState = FollowState.Unfollowed
) : UIContract.State

data class ProfileUI(
    val isLoading: Boolean = false,
    val userId: String = "",
    val userName: String = "",
    val avatarBytes: ByteArray? = null,
    val experienceData: ExperienceData? = null,
)

data class NotesUI(
    val isLoading: Boolean = false,
    val items: List<Note> = emptyList()
)

enum class FollowState {
    Followed,
    Unfollowed
}