package com.velkonost.getbetter.shared.features.profile

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.profile.models.ProfileAction
import com.velkonost.getbetter.shared.features.profile.models.ProfileNavigation
import com.velkonost.getbetter.shared.features.profile.models.ProfileViewState

class ProfileViewModel
internal constructor(

) : BaseViewModel<ProfileViewState, ProfileAction, ProfileNavigation, Nothing>(
    initialState = ProfileViewState()
) {
    override fun dispatch(action: ProfileAction) = when(action) {

        else -> {}
    }

}