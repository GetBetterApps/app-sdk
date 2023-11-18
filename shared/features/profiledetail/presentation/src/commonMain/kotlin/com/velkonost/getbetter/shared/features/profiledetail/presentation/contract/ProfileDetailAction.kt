package com.velkonost.getbetter.shared.features.profiledetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface ProfileDetailAction : UIContract.Action {
    data class Load(val userId: String) : ProfileDetailAction

    data object FollowClick : ProfileDetailAction

    data object UnfollowClick : ProfileDetailAction

    data object NotesLoadNextPage : ProfileDetailAction
}