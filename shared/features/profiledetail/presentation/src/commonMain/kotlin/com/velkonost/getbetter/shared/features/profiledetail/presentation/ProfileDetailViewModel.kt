package com.velkonost.getbetter.shared.features.profiledetail.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.follows.api.FollowsRepository
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailAction
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailEvent
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailNavigation
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository

class ProfileDetailViewModel
internal constructor(
    private val userInfoRepository: UserInfoRepository,
    private val followsRepository: FollowsRepository
) : BaseViewModel<ProfileDetailViewState, ProfileDetailAction, ProfileDetailNavigation, ProfileDetailEvent>(
    initialState = ProfileDetailViewState()
) {
    override fun dispatch(action: ProfileDetailAction) = when (action) {
        else -> {

        }
    }

}