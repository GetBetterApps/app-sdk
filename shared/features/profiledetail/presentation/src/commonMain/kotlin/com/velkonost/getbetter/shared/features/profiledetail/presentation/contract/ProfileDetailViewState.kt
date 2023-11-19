package com.velkonost.getbetter.shared.features.profiledetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.user.ExperienceData
import com.velkonost.getbetter.shared.core.util.PrefetchDistanceValue
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class ProfileDetailViewState(
    val profileData: ProfileUI = ProfileUI(),
    val notesData: NotesUI = NotesUI(),
    val followData: FollowUI = FollowUI()
) : UIContract.State

data class ProfileUI(
    val isLoading: Boolean = false,
    val userId: String = "",
    val userName: String = "",
    val avatarBytes: ByteArray? = null,
    val experienceData: ExperienceData? = null,
)

data class NotesUI(
    val isLoading: Boolean = true,
    val loadMorePrefetch: Int = PrefetchDistanceValue,
    val items: List<Note> = emptyList()
)

data class FollowUI(
    val isLoading: Boolean = true,
    val state: FollowState = FollowState.Unfollowed
)

enum class FollowState {
    Followed,
    Unfollowed;

    companion object {
        fun FollowState.reverseState(): FollowState {
            return if (this == Followed) Unfollowed
            else Followed
        }
    }
}